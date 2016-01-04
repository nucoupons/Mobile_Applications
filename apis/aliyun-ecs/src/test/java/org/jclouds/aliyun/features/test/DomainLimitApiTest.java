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
package org.jclouds.aliyun.features.test;

import static org.jclouds.reflect.Reflection2.method;

import java.io.IOException;

import org.jclouds.aliyun.domain.ResourceLimit;
import org.jclouds.aliyun.domain.ResourceLimit.ResourceType;
import org.jclouds.aliyun.features.DomainLimitApi;
import org.jclouds.aliyun.internal.test.BaseAliyunApiTest;
import org.jclouds.fallbacks.MapHttp4xxCodesToExceptions;
import org.jclouds.http.functions.ParseFirstJsonValueNamed;
import org.jclouds.rest.internal.GeneratedHttpRequest;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableList;
import com.google.common.reflect.Invokable;
/**
 * Tests behavior of {@code DomainLimitApi}
 */
@Test(groups = "unit", testName = "DomainLimitApiTest")
public class DomainLimitApiTest extends BaseAliyunApiTest<DomainLimitApi> {

   public void testUpdateResourceLimit() throws SecurityException, NoSuchMethodException, IOException {
      Invokable<?, ?> method = method(DomainLimitApi.class, "updateResourceLimit", ResourceLimit.class);
      GeneratedHttpRequest httpRequest = processor.createRequest(method, ImmutableList.<Object> of(
            ResourceLimit.builder().resourceType(ResourceType.SNAPSHOT).account("foo").domainId("100").max(101).build()));

      assertRequestLineEquals(
            httpRequest,
            "GET http://localhost:8080/client/api?response=json&command=updateResourceLimit&resourcetype=3&account=foo&domainid=100&max=101 HTTP/1.1");
      assertNonPayloadHeadersEqual(httpRequest, "Accept: application/json\n");
      assertPayloadEquals(httpRequest, null, null, false);

      assertResponseParserClassEquals(method, httpRequest, ParseFirstJsonValueNamed.class);
      assertSaxResponseParserClassEquals(method, null);
      assertFallbackClassEquals(method, MapHttp4xxCodesToExceptions.class);

      checkFilters(httpRequest);

   }
}