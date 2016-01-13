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
package org.jclouds.aliyun.ecs.compute.internal;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.jclouds.Context;
import org.jclouds.aliyun.ecs.AliyunEcsApi;
import org.jclouds.aliyun.ecs.compute.AliyunEcsComputeService;
import org.jclouds.aliyun.ecs.compute.AliyunEcsComputeServiceContext;
import org.jclouds.compute.Utils;
import org.jclouds.ecs.compute.internal.EcsComputeServiceContextImpl;
import org.jclouds.location.Provider;

import com.google.common.reflect.TypeToken;

@Singleton
public class AliyunEcsComputeServiceContextImpl extends EcsComputeServiceContextImpl implements
         AliyunEcsComputeServiceContext {
   @Inject
   public AliyunEcsComputeServiceContextImpl(@Provider Context backend,
            @Provider TypeToken<? extends Context> backendType, AliyunEcsComputeService computeService, Utils utils, AliyunEcsApi api) {
      super(backend, backendType, computeService, utils);
      
   }

   @Override
   public AliyunEcsComputeService getComputeService() {
      return AliyunEcsComputeService.class.cast(super.getComputeService());
   }

}
