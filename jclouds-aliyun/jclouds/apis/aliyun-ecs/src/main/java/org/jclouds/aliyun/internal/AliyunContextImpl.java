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
package org.jclouds.aliyun.internal;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.jclouds.Context;
import org.jclouds.aliyun.AliyunApi;
import org.jclouds.aliyun.AliyunContext;
import org.jclouds.aliyun.AliyunDomainApi;
import org.jclouds.aliyun.AliyunGlobalApi;
import org.jclouds.compute.ComputeService;
import org.jclouds.compute.Utils;
import org.jclouds.compute.internal.ComputeServiceContextImpl;
import org.jclouds.location.Provider;
import org.jclouds.rest.ApiContext;

import com.google.common.reflect.TypeToken;

@Singleton
public class AliyunContextImpl extends ComputeServiceContextImpl implements AliyunContext {
   private final AliyunApi client;
   private final ApiContext<AliyunDomainApi> domainContext;
   private final ApiContext<AliyunGlobalApi> globalContext;

   @Inject
   AliyunContextImpl(@Provider Context backend, @Provider TypeToken<? extends Context> backendType,
         ComputeService computeService, Utils utils, AliyunApi client,
         ApiContext<AliyunDomainApi> domainContext,
         ApiContext<AliyunGlobalApi> globalContext) {
      super(backend, backendType, computeService, utils);
      this.client = client;
      this.domainContext = domainContext;
      this.globalContext = globalContext;
   }

   @Override
   public AliyunApi getApi() {
      return client;
   }

   @Override
   public AliyunDomainApi getDomainApi() {
      return domainContext.getApi();
   }

   @Override
   public AliyunGlobalApi getGlobalApi() {
      return globalContext.getApi();
   }

}
