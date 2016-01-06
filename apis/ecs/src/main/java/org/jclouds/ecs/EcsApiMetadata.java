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
package org.jclouds.ecs;

import java.net.URI;
import java.util.Properties;

import org.jclouds.apis.ApiMetadata;
import org.jclouds.rest.internal.BaseHttpApiMetadata;

import com.google.auto.service.AutoService;

@AutoService(ApiMetadata.class)
public final class EcsApiMetadata extends BaseHttpApiMetadata<EcsApi> {

   @Override
   public Builder toBuilder() {
      return new Builder().fromApiMetadata(this);
   }

   public EcsApiMetadata() {
      super(new Builder());
   }

   protected EcsApiMetadata(Builder builder) {
      super(builder);
   }

   public static Properties defaultProperties() {
      Properties properties = BaseHttpApiMetadata.defaultProperties();
     
      return properties;
   }

   public static final class Builder extends BaseHttpApiMetadata.Builder<EcsApi, Builder> {
      public Builder() {
    	  
    	  id("ecs")
          .name("Aliyun Ecs API")
          .identityName("AccessKeyId")
          .credentialName("Secret Key")
          .documentation(URI.create("http://download.cloud.com/releases/2.2.0/api_2.2.12/TOC_User.html"))
          .defaultEndpoint("https://ecs.aliyuncs.com/")
          .version("2014-05-26")
          .defaultProperties(EcsApiMetadata.defaultProperties());
//         .view(EC2ComputeServiceContext.class)
//         .defaultModules(ImmutableSet.<Class<? extends Module>>of(EC2HttpApiModule.class, EC2ResolveImagesModule.class, EC2ComputeServiceContextModule.class));
      }

      @Override
      public ApiMetadata build() {
         return new EcsApiMetadata(this);
      }

      @Override
      protected Builder self() {
         return this;
      }
   }
}
