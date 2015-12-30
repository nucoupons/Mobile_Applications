/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jclouds.aliyun.features;

import static com.google.common.base.Charsets.UTF_8;
import static com.google.common.hash.Hashing.md5;
import static com.google.common.io.BaseEncoding.base16;
import static org.jclouds.aliyun.features.GlobalAccountApiLiveTest.createTestAccount;
import static org.jclouds.aliyun.features.GlobalUserApiLiveTest.createTestUser;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.net.URI;

import org.jclouds.aliyun.domain.Account;
import org.jclouds.aliyun.domain.ApiKeyPair;
import org.jclouds.aliyun.domain.LoginResponse;
import org.jclouds.aliyun.domain.User;
import org.jclouds.aliyun.internal.BaseCloudStackApiLiveTest;
import org.jclouds.aliyun.util.ApiKeyPairs;
import org.jclouds.rest.AuthorizationException;
import org.testng.annotations.Test;

/**
 * Tests behavior of {@code SessionApi}
 */
@Test(groups = "live", singleThreaded = true, testName = "SessionApiLiveTest")
public class SessionApiLiveTest extends BaseCloudStackApiLiveTest {

   @Test
   public void testCreateContextUsingUserAndPasswordAuthentication() {
      skipIfNotGlobalAdmin();

      Account testAccount = null;
      User testUser = null;

      String prefix = this.prefix + "-session";
      try {
         testAccount = createTestAccount(globalAdminClient, prefix);
         testUser = createTestUser(globalAdminClient, testAccount, prefix);

         String expectedUsername = prefix + "-user";
         assertEquals(testUser.getName(), expectedUsername);

         checkLoginAsTheNewUser(expectedUsername);

         ApiKeyPair expected = globalAdminClient.getUserClient().registerUserKeys(testUser.getId());
         ApiKeyPair actual = ApiKeyPairs.loginToEndpointAsUsernameInDomainWithPasswordAndReturnApiKeyPair(
            URI.create(endpoint), prefix + "-user", "password", "");

         assertEquals(actual, expected);

      } finally {
         if (testUser != null)
            globalAdminClient.getUserClient().deleteUser(testUser.getId());
         if (testAccount != null)
            globalAdminClient.getAccountApi().deleteAccount(testAccount.getId());
      }
   }

   @Test(expectedExceptions = AuthorizationException.class)
   public void testTryToGetApiKeypairWithWrongCredentials() {
      ApiKeyPairs.loginToEndpointAsUsernameInDomainWithPasswordAndReturnApiKeyPair(
         URI.create(endpoint), "dummy-missing-user", "with-a-wrong-password", "");
   }

   private void checkLoginAsTheNewUser(String expectedUsername) {
      LoginResponse response = globalAdminClient.getSessionApi().loginUserInDomainWithHashOfPassword(
            expectedUsername, "", base16().lowerCase().encode(md5().hashString("password", UTF_8).asBytes()));

      assertNotNull(response);
      assertNotNull(response.getSessionKey());
      assertNotNull(response.getJSessionId());

      client.getSessionApi().logoutUser(response.getSessionKey());
   }
}
