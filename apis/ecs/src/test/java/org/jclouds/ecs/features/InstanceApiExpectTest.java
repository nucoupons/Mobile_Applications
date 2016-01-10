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

import java.util.Properties;

import org.jclouds.ecs.EcsApi;
import org.jclouds.ecs.internal.BaseEcsApiExpectTest;
import org.jclouds.http.HttpRequest;
import org.jclouds.http.HttpResponse;
import org.testng.annotations.Test;

/**
 *
 * @see InstanceApi
 */
@Test(groups = "unit")
public class InstanceApiExpectTest extends BaseEcsApiExpectTest<EcsApi> {

	protected Properties setupProperties() {
		Properties props = super.setupProperties();
		return props;
	}

	protected final HttpRequest login = HttpRequest.builder().method("GET")
			.endpoint("https://ecs.aliyuncs.com/")
			.addQueryParam("Format", "json")
			.addQueryParam("AccessKeyId", "Z1mdYKAUt4q2OzyDhSd5qcnMUamQdD")
			.addQueryParam("Timestamp", "2012-06-01T12:00:00Z").build();

	protected final HttpResponse loginResponse = HttpResponse.builder()
			.statusCode(200).build();

	public void testInstance() throws Exception {

		EcsApi api = requestSendsResponse(login, loginResponse);
		
		
		api.getInstanceApi().get().listInstances();
		

	}
}
