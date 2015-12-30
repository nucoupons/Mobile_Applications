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
package org.jclouds.aliyun.compute.extensions;

import org.jclouds.aliyun.AliyunApi;
import org.jclouds.aliyun.domain.Zone;
import org.jclouds.compute.domain.Template;
import org.jclouds.compute.extensions.internal.BaseSecurityGroupExtensionLiveTest;
import org.jclouds.sshj.config.SshjSshClientModule;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.inject.Module;

/**
 * Live test for Aliyun {@link org.jclouds.compute.extensions.SecurityGroupExtension} implementation.
 */
@Test(groups = "live", singleThreaded = true, testName = "AliyunSecurityGroupExtensionLiveTest")
public class AliyunSecurityGroupExtensionLiveTest extends BaseSecurityGroupExtensionLiveTest {

   protected Zone zone;

   public AliyunSecurityGroupExtensionLiveTest() {
      provider = "aliyun";
   }

   @BeforeClass(groups = { "integration", "live" })
   public void setupContext() {
      super.setupContext();

      AliyunApi api = view.unwrapApi(AliyunApi.class);
      for (Zone z : api.getZoneApi().listZones()) {
         if (z.isSecurityGroupsEnabled()) {
            zone = z;
            break;
         }
      }

      if (zone == null)
         securityGroupsSupported = false;
   }

   protected Module getSshModule() {
      return new SshjSshClientModule();
   }

   @Override
   public Template getNodeTemplate() {
      return view.getComputeService().templateBuilder().locationId(zone.getId()).build();
   }
}
