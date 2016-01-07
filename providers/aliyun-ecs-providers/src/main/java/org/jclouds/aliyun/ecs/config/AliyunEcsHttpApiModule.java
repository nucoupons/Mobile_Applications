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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.inject.Singleton;

import org.jclouds.aliyun.ecs.AliyunEcsApi;
import org.jclouds.aws.filters.FormSigner;
import org.jclouds.aws.filters.FormSignerV4;
import org.jclouds.date.DateService;
import org.jclouds.ecs.EcsApi;
import org.jclouds.ecs.config.BaseEcsHttpApiModule;
import org.jclouds.rest.ConfiguresHttpApi;

import com.google.inject.Provides;

/**
 * Configures the EC2 connection.
 */
@ConfiguresHttpApi
public class AliyunEcsHttpApiModule extends BaseEcsHttpApiModule<AliyunEcsApi> {

   private final SimpleDateFormat iso8601 = new SimpleDateFormat("yyyyMMdd'T'HHmmss'Z'");

   public AliyunEcsHttpApiModule() {
      super(AliyunEcsApi.class);
      iso8601.setTimeZone(TimeZone.getTimeZone("GMT"));
   }

   @Singleton
   @Provides
   EcsApi provide(AliyunEcsApi in) {
      return in;
   }

  

   @Override
   protected void configure() {
      bind(FormSigner.class).to(FormSignerV4.class);
      super.configure();
   }

   @Override protected String provideTimeStamp(DateService dateService) {
      // 20120416T155408Z not 2012-04-16T15:54:08Z
      return iso8601.format(new Date());
   }
}
