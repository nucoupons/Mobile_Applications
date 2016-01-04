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
package org.jclouds.aliyun;
import static org.jclouds.aliyun.config.AliyunProperties.AUTO_GENERATE_KEYPAIRS;
import static org.jclouds.reflect.Reflection2.typeToken;

import java.net.URI;
import java.util.Properties;

import org.jclouds.aliyun.compute.config.AliyunComputeServiceContextModule;
import org.jclouds.aliyun.config.AliyunHttpApiModule;
import org.jclouds.aliyun.config.AliyunParserModule;
import org.jclouds.apis.ApiMetadata;
import org.jclouds.rest.internal.BaseHttpApiMetadata;

import com.google.auto.service.AutoService;
import com.google.common.collect.ImmutableSet;
import com.google.inject.Module;
/**
 * Implementation of {@link ApiMetadata} for Citrix/Apache Aliyun api.
 */
@AutoService(ApiMetadata.class)
public class AliyunApiMetadata extends BaseHttpApiMetadata<AliyunApi> {
   
   @Override
   public Builder toBuilder() {
      return new Builder().fromApiMetadata(this);
   }

   public AliyunApiMetadata() {
      this(new Builder());
   }

   protected AliyunApiMetadata(Builder builder) {
      super(builder);
   }

   public static Properties defaultProperties() {
      Properties properties = BaseHttpApiMetadata.defaultProperties();
      properties.setProperty("jclouds.ssh.max-retries", "7");
      properties.setProperty("jclouds.ssh.retry-auth", "true");
      properties.setProperty(AUTO_GENERATE_KEYPAIRS, "false");
      return properties;
   }

   public static class Builder extends BaseHttpApiMetadata.Builder<AliyunApi, Builder> {

      protected Builder() {
         id("aliyun-ecs")
         .name("Aliyun Ecs API")
         .identityName("AccessKeyId")
         .credentialName("Secret Key")
         .documentation(URI.create("http://download.cloud.com/releases/2.2.0/api_2.2.12/TOC_User.html"))
         .defaultEndpoint("https://ecs.aliyuncs.com/")
         .defaultIdentity("AccessKeyId")
         .version("2.2")
         .view(typeToken(AliyunContext.class))
         .defaultProperties(AliyunApiMetadata.defaultProperties())
         .defaultModules(ImmutableSet.<Class<? extends Module>> builder()
                                     .add(AliyunParserModule.class)
                                     .add(AliyunHttpApiModule.class)
                                     .add(AliyunComputeServiceContextModule.class).build());
      }
      
      @Override
      public AliyunApiMetadata build() {
         return new AliyunApiMetadata(this);
      }

      @Override
      protected Builder self() {
         return this;
      }
   }
}
