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
package org.jclouds.aliyun.ecs.config;



import static org.jclouds.Constants.PROPERTY_SESSION_INTERVAL;

import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.jclouds.aliyun.ecs.AliyunEcsApi;
import org.jclouds.date.TimeStamp;
import org.jclouds.rest.ConfiguresHttpApi;
import org.jclouds.rest.config.HttpApiModule;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.inject.Provides;
import com.google.inject.name.Named;

/**
 * Configures the EC2 connection.
 */
@ConfiguresHttpApi
public class AliyunEcsHttpApiModule extends HttpApiModule<AliyunEcsApi> {

	private final SimpleDateFormat iso8601 = new SimpleDateFormat(
			"yyyyMMdd'T'HHmmss'Z'");

	public AliyunEcsHttpApiModule() {
		iso8601.setTimeZone(TimeZone.getTimeZone("GMT"));
	}

	@Provides
	@TimeStamp
	protected Long provideTimeStamp(@TimeStamp Supplier<Long> cache) {
		return cache.get();
	}

	/**
	 * borrowing concurrency code to ensure that caching takes place properly
	 */
	@Provides
	@TimeStamp
	Supplier<Long> provideTimeStampCache(
			@Named(PROPERTY_SESSION_INTERVAL) long seconds) {
		return Suppliers.memoizeWithExpiration(new Supplier<Long>() {
			public Long get() {
				return System.currentTimeMillis() / 1000;
			}
		}, seconds, TimeUnit.SECONDS);
	}



}
