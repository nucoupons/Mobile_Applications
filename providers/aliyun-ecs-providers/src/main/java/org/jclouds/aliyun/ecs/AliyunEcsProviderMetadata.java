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

import org.jclouds.providers.ProviderMetadata;
import org.jclouds.providers.internal.BaseProviderMetadata;

import com.google.auto.service.AutoService;

/**
 * Implementation of {@ link org.jclouds.types.ProviderMetadata} for Amazon's
 * Elastic Compute Cloud (EC2) provider.
 */
@AutoService(ProviderMetadata.class)
public class AliyunEcsProviderMetadata extends BaseProviderMetadata {

   public static Builder builder() {
      return new Builder();
   }

   @Override
   public Builder toBuilder() {
      return builder().fromProviderMetadata(this);
   }
   
   public AliyunEcsProviderMetadata() {
      super(builder());
   }

   public AliyunEcsProviderMetadata(Builder builder) {
      super(builder);
   }

   public static Properties defaultProperties() {
      Properties properties = new Properties();
  
      return properties;
   }
   
   public static class Builder extends BaseProviderMetadata.Builder {

      protected Builder() {
    	 
         id("aliyun-ecs")
         .name("Aliyun Elastic Compute Service (ECS)")
         .apiMetadata(new AliyunEcsApiMetadata())
         .endpoint("https://ecs.aliyuncs.com/")
         .homepage(URI.create("http://www.aliyun.com"))
         .console(URI.create("https://ecs.console.aliyun.com/#/"))
         .defaultProperties(AliyunEcsProviderMetadata.defaultProperties());
        
      }

      @Override
      public AliyunEcsProviderMetadata build() {
         return new AliyunEcsProviderMetadata(this);
      }

      @Override
      public Builder fromProviderMetadata(
            ProviderMetadata in) {
         super.fromProviderMetadata(in);
         return this;
      }

   }
}
