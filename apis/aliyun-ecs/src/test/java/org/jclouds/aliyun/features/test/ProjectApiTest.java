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

import org.jclouds.Fallbacks.EmptySetOnNotFoundOr404;
import org.jclouds.Fallbacks.NullOnNotFoundOr404;
import org.jclouds.aliyun.features.ProjectApi;
import org.jclouds.aliyun.internal.test.BaseAliyunApiTest;
import org.jclouds.aliyun.options.ListProjectsOptions;
import org.jclouds.functions.IdentityFunction;
import org.jclouds.http.functions.ParseFirstJsonValueNamed;
import org.jclouds.rest.internal.GeneratedHttpRequest;
import org.testng.annotations.Test;

import com.google.common.base.Functions;
import com.google.common.collect.ImmutableList;
import com.google.common.reflect.Invokable;

/**
 * Tests behavior of {@code ProjectApi}
 */
// NOTE:without testName, this will not call @Before* and fail w/NPE during
// surefire
@Test(groups = "unit", testName = "ProjectApiTest")
public class ProjectApiTest extends BaseAliyunApiTest<ProjectApi> {

   public void testListProjects() throws SecurityException, NoSuchMethodException, IOException {
      Invokable<?, ?> method = method(ProjectApi.class, "listProjects", ListProjectsOptions[].class);
      GeneratedHttpRequest httpRequest = processor.createRequest(method, ImmutableList.of());

      assertRequestLineEquals(httpRequest,
            "GET http://localhost:8080/client/api?response=json&command=listProjects&listAll=true HTTP/1.1");
      assertNonPayloadHeadersEqual(httpRequest, "Accept: application/json\n");
      assertPayloadEquals(httpRequest, null, null, false);

      assertResponseParserClassEquals(method, httpRequest, ParseFirstJsonValueNamed.class);
      assertSaxResponseParserClassEquals(method, null);
      assertFallbackClassEquals(method, EmptySetOnNotFoundOr404.class);

      checkFilters(httpRequest);

   }

   public void testListProjectsOptions() throws SecurityException, NoSuchMethodException, IOException {
      Invokable<?, ?> method = method(ProjectApi.class, "listProjects", ListProjectsOptions[].class);
      GeneratedHttpRequest httpRequest = processor.createRequest(method, ImmutableList.<Object> of(
            ListProjectsOptions.Builder.accountInDomain("jclouds", "123")));

      assertRequestLineEquals(httpRequest,
            "GET http://localhost:8080/client/api?response=json&command=listProjects&listAll=true&account=jclouds&domainid=123 HTTP/1.1");
      assertNonPayloadHeadersEqual(httpRequest, "Accept: application/json\n");
      assertPayloadEquals(httpRequest, null, null, false);

      assertResponseParserClassEquals(method, httpRequest, ParseFirstJsonValueNamed.class);
      assertSaxResponseParserClassEquals(method, null);
      assertFallbackClassEquals(method, EmptySetOnNotFoundOr404.class);

      checkFilters(httpRequest);

   }

   public void testGetAccount() throws SecurityException, NoSuchMethodException, IOException {
      Invokable<?, ?> method = method(ProjectApi.class, "getProject", String.class);
      GeneratedHttpRequest httpRequest = processor.createRequest(method, ImmutableList.<Object> of("3"));

      assertRequestLineEquals(httpRequest,
            "GET http://localhost:8080/client/api?response=json&command=listProjects&listAll=true&id=3 HTTP/1.1");
      assertNonPayloadHeadersEqual(httpRequest, "Accept: application/json\n");
      assertPayloadEquals(httpRequest, null, null, false);

      assertResponseParserClassEquals(method, httpRequest,
            Functions.compose(IdentityFunction.INSTANCE, IdentityFunction.INSTANCE).getClass());
      assertSaxResponseParserClassEquals(method, null);
      assertFallbackClassEquals(method, NullOnNotFoundOr404.class);

      checkFilters(httpRequest);

   }

}