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

import static org.jclouds.aliyun.features.GlobalAccountApiLiveTest.createTestAccount;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import org.jclouds.aliyun.domain.Account;
import org.jclouds.aliyun.domain.AsyncCreateResponse;
import org.jclouds.aliyun.domain.AsyncJob;
import org.jclouds.aliyun.internal.BaseAliyunApiLiveTest;
import org.testng.annotations.Test;

/**
 * Tests behavior of {@code DomainAccountApi}
 */
@Test(groups = "live", singleThreaded = true, testName = "DomainAccountApiLiveTest")
public class DomainAccountApiLiveTest extends BaseAliyunApiLiveTest {

   @Test
   public void testEnableDisableAccount() {
      skipIfNotGlobalAdmin();

      Account testAccount = null;
      try {
         testAccount = createTestAccount(globalAdminClient, prefix);
         
         AsyncCreateResponse response = domainAdminClient.getAccountApi()
            .disableAccount(testAccount.getName(), testAccount.getDomainId(), false);
         assertNotNull(response);
         assertTrue(adminJobComplete.apply(response.getJobId()));

         AsyncJob<Account> job = domainAdminClient.getAsyncJobApi().getAsyncJob(response.getJobId());
         assertEquals(job.getResult().getState(), Account.State.DISABLED);

         Account updated = domainAdminClient.getAccountApi()
            .enableAccount(testAccount.getName(), testAccount.getDomainId());
         assertNotNull(updated);
         assertEquals(updated.getState(), Account.State.ENABLED);

      } finally {
         if (testAccount != null) {
            globalAdminClient.getAccountApi().deleteAccount(testAccount.getId());
         }
      }
   }

}
