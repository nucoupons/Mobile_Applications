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
package org.jclouds.aliyun.ecs;

import java.net.URI;
import java.util.Properties;

import org.jclouds.ecs.EcsApiMetadata;
import org.jclouds.rest.internal.BaseHttpApiMetadata;

public final class AliyunEcsApiMetadata extends
		BaseHttpApiMetadata<AliyunEcsApi> {

	public Builder toBuilder() {
		return new Builder().fromApiMetadata(this);
	}

	public AliyunEcsApiMetadata() {
		super(new Builder());
	}

	protected AliyunEcsApiMetadata(Builder builder) {
		super(builder);
	}

	public static Properties defaultProperties() {
		Properties properties = EcsApiMetadata.defaultProperties();

		// authorized key executes after ssh has started.
		properties.setProperty("jclouds.ssh.max-retries", "7");
		properties.setProperty("jclouds.ssh.retry-auth", "true");
		return properties;
	}

	public static final class Builder extends
			BaseHttpApiMetadata.Builder<AliyunEcsApi, Builder> {
		public Builder() {
			id("aliyun-ecs")
					.name("Aliyun Ecs API")
					.identityName("AccessKeyId")
					.credentialName("Secret Key")
					.documentation(
							URI.create("http://download.cloud.com/releases/2.2.0/api_2.2.12/TOC_User.html"))
					.defaultEndpoint("https://ecs.aliyuncs.com/")
					.version("2014-05-26")
					.defaultProperties(EcsApiMetadata.defaultProperties());
			// .view(AWSEC2ComputeServiceContext.class)
			// .defaultModules(ImmutableSet.<Class<? extends
			// Module>>of(AWSEC2HttpApiModule.class,
			// EC2ResolveImagesModule.class,
			// AWSEC2ComputeServiceContextModule.class));
		}

		public AliyunEcsApiMetadata build() {
			return new AliyunEcsApiMetadata(this);
		}

		protected Builder self() {
			return this;
		}
	}
}
