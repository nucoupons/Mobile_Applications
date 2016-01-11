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
package org.jclouds.ecs.features;

import static org.jclouds.reflect.Reflection2.method;

import java.io.IOException;

import org.jclouds.Fallbacks.EmptySetOnNotFoundOr404;
import org.jclouds.ecs.internal.BaseEcsApiTest;
import org.jclouds.http.functions.ParseFirstJsonValueNamed;
import org.jclouds.rest.internal.GeneratedHttpRequest;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableList;
import com.google.common.reflect.Invokable;

/**
 * Tests behavior of {@code InstanceApi}
 */
// NOTE:without testName, this will not call @Before* and fail w/NPE during
// surefire
@Test(groups = "unit", testName = "InstanceApiTest")
public class InstanceApiTest extends BaseEcsApiTest<InstanceApi> {

	public void testListInstances() throws SecurityException,
			NoSuchMethodException, IOException {
		Invokable<?, ?> method = method(InstanceApi.class, "listInstances");
		@SuppressWarnings("deprecation")
		GeneratedHttpRequest httpRequest = processor.createRequest(method,
				ImmutableList.of());

		assertRequestLineEquals(
				httpRequest,
				"GET https://ecs.aliyuncs.com/?Format=json&Version=2014-05-26&Action=listInstances HTTP/1.1");
		assertNonPayloadHeadersEqual(httpRequest, "Accept: application/json\n");
		assertPayloadEquals(httpRequest, null, null, false);
		assertResponseParserClassEquals(method, httpRequest,
				ParseFirstJsonValueNamed.class);
		assertSaxResponseParserClassEquals(method, null);
		assertFallbackClassEquals(method, EmptySetOnNotFoundOr404.class);

	}

}
