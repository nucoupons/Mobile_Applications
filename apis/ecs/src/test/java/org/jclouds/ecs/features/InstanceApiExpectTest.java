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

import static org.testng.AssertJUnit.assertEquals;

import java.util.Properties;

import org.jclouds.ecs.EcsApi;
import org.jclouds.ecs.compute.domain.Instance;
import org.jclouds.ecs.internal.BaseEcsApiExpectTest;
import org.jclouds.http.HttpRequest;
import org.jclouds.http.HttpResponse;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableSet;

/**
 *
 * @see InstanceApi
 */
@Test(groups = "unit", testName = "InstanceApiExpectTest")
public class InstanceApiExpectTest extends BaseEcsApiExpectTest<EcsApi> {

	protected Properties setupProperties() {
		Properties props = super.setupProperties();
		return props;
	}

	public void testListInstancesWhenResponseIs2xx() throws Exception {

		EcsApi client = requestSendsResponse(
				HttpRequest
						.builder()
						.method("GET")
						.endpoint("https://ecs.aliyuncs.com/?Format=json&Version=2014-05-26&Action=DescribeRegions&RegionId=cn-qingdao&AccessKeyId=bnF9nNdDFCTwM5mF&SignatureMethod=HMAC-SHA1&Timestamp=2016-01-10T13%3A40%3A34Z&SignatureVersion=1.0&SignatureNonce=1630755302&Signature=8HtY1MFN5UufDWgPWNRSJ5OjkFA%3D")
						.addHeader("Accept", "application/json").build(),
				HttpResponse.builder().statusCode(200)
						.payload(payloadFromResource("/listInstance.json"))
						.build());

		assertEquals(
				client.getInstanceApi().get().listInstances(),
				ImmutableSet.<Instance> of(Instance.builder().account("admin")
						.id("id").build()));
	}

}
