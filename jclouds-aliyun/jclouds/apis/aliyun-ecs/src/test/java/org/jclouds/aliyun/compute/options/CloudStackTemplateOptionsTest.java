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
package org.jclouds.aliyun.compute.options;

import com.google.common.collect.ImmutableSet;

import org.jclouds.aliyun.compute.options.AliyunTemplateOptions;
import org.jclouds.compute.options.TemplateOptions;
import org.testng.annotations.Test;
import org.testng.collections.Maps;

import java.util.Map;

import static org.jclouds.aliyun.compute.options.AliyunTemplateOptions.Builder.account;
import static org.jclouds.aliyun.compute.options.AliyunTemplateOptions.Builder.dataDiskSize;
import static org.jclouds.aliyun.compute.options.AliyunTemplateOptions.Builder.diskOfferingId;
import static org.jclouds.aliyun.compute.options.AliyunTemplateOptions.Builder.domainId;
import static org.jclouds.aliyun.compute.options.AliyunTemplateOptions.Builder.generateKeyPair;
import static org.jclouds.aliyun.compute.options.AliyunTemplateOptions.Builder.generateSecurityGroup;
import static org.jclouds.aliyun.compute.options.AliyunTemplateOptions.Builder.ipOnDefaultNetwork;
import static org.jclouds.aliyun.compute.options.AliyunTemplateOptions.Builder.ipsToNetworks;
import static org.jclouds.aliyun.compute.options.AliyunTemplateOptions.Builder.keyPair;
import static org.jclouds.aliyun.compute.options.AliyunTemplateOptions.Builder.networks;
import static org.jclouds.aliyun.compute.options.AliyunTemplateOptions.Builder.securityGroupId;
import static org.jclouds.aliyun.compute.options.AliyunTemplateOptions.Builder.securityGroupIds;
import static org.jclouds.aliyun.compute.options.AliyunTemplateOptions.Builder.setupStaticNat;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

/**
 * Tests possible uses of {@code CloudStackTemplateOptions} and
 * {@code CloudStackTemplateOptions.Builder.*}.
 */
// NOTE:without testName, this will not call @Before* and fail w/NPE during
// surefire
@Test(groups = "unit", testName = "CloudStackTemplateOptionsTest")
public class CloudStackTemplateOptionsTest {
   //TODO: test clone();
   
   @Test
   public void testAs() {
      TemplateOptions options = new AliyunTemplateOptions();
      assertEquals(options.as(AliyunTemplateOptions.class), options);
   }

   @Test
   public void testDefaultSecurityGroupIds() {
      TemplateOptions options = new AliyunTemplateOptions();
      assertEquals(options.as(AliyunTemplateOptions.class).getSecurityGroupIds(), ImmutableSet.of());
   }

   @Test
   public void testSecurityGroupId() {
      TemplateOptions options = new AliyunTemplateOptions().securityGroupId("3");
      assertEquals(options.as(AliyunTemplateOptions.class).getSecurityGroupIds(), ImmutableSet.of("3"));
   }

   @Test
   public void testSecurityGroupIdStatic() {
      TemplateOptions options = securityGroupId("3");
      assertEquals(options.as(AliyunTemplateOptions.class).getSecurityGroupIds(), ImmutableSet.of("3"));
   }

   @Test
   public void testSecurityGroupIds() {
      TemplateOptions options = new AliyunTemplateOptions().securityGroupIds(ImmutableSet.of("3"));
      assertEquals(options.as(AliyunTemplateOptions.class).getSecurityGroupIds(), ImmutableSet.of("3"));
   }

   @Test
   public void testSecurityGroupIdsStatic() {
      TemplateOptions options = securityGroupIds(ImmutableSet.of("3"));
      assertEquals(options.as(AliyunTemplateOptions.class).getSecurityGroupIds(), ImmutableSet.of("3"));
   }

   @Test
   public void testGenerateSecurityGroupDefaultsFalse() {
      TemplateOptions options = new AliyunTemplateOptions();
      assertFalse(options.as(AliyunTemplateOptions.class)
         .shouldGenerateSecurityGroup());
   }

   @Test
   public void testGenerateSecurityGroup() {
      TemplateOptions options = new AliyunTemplateOptions().generateSecurityGroup(true);
      assertTrue(options.as(AliyunTemplateOptions.class)
         .shouldGenerateSecurityGroup());
   }

   @Test
   public void testGenerateSecurityGroupStatic() {
      TemplateOptions options = generateSecurityGroup(true);
      assertTrue(options.as(AliyunTemplateOptions.class)
         .shouldGenerateSecurityGroup());
   }

   @Test
   public void testDefaultNetworkIds() {
      TemplateOptions options = new AliyunTemplateOptions();
      assertEquals(options.as(AliyunTemplateOptions.class).getNetworks(), ImmutableSet.of());
   }

   @Test
   public void testNetworkId() {
      TemplateOptions options = new AliyunTemplateOptions().networks("3");
      assertEquals(options.as(AliyunTemplateOptions.class).getNetworks(), ImmutableSet.of("3"));
   }

   @Test
   public void testNetworkIdStatic() {
      TemplateOptions options = networks(ImmutableSet.of("3"));
      assertEquals(options.as(AliyunTemplateOptions.class).getNetworks(), ImmutableSet.of("3"));
   }

   @Test
   public void testNetworkIds() {
      TemplateOptions options = new AliyunTemplateOptions().networks(ImmutableSet.of("3"));
      assertEquals(options.as(AliyunTemplateOptions.class).getNetworks(), ImmutableSet.of("3"));
   }

   @Test
   public void testNetworkIdsStatic() {
      TemplateOptions options = networks(ImmutableSet.of("3"));
      assertEquals(options.as(AliyunTemplateOptions.class).getNetworks(), ImmutableSet.of("3"));
   }

   @Test
   public void testIpOnDefaultNetwork() {
      TemplateOptions options = new AliyunTemplateOptions().ipOnDefaultNetwork("10.0.0.1");
      assertEquals(options.as(AliyunTemplateOptions.class).getIpOnDefaultNetwork(), "10.0.0.1");
   }

   @Test
   public void testIpOnDefaultNetworkStatic() {
      TemplateOptions options = ipOnDefaultNetwork("10.0.0.1");
      assertEquals(options.as(AliyunTemplateOptions.class).getIpOnDefaultNetwork(), "10.0.0.1");
   }

   @Test
   public void testIpsToNetwork() {
      Map<String, String> ipsToNetworks = Maps.newHashMap();
      ipsToNetworks.put("10.0.0.1", "5");

      TemplateOptions options = new AliyunTemplateOptions().ipsToNetworks(ipsToNetworks);
      assertEquals(options.as(AliyunTemplateOptions.class)
         .getIpsToNetworks().get("10.0.0.1"), "5");
   }

   @Test
   public void testIpsToNetworkStatic() {
      Map<String, String> ipsToNetworks = Maps.newHashMap();
      ipsToNetworks.put("10.0.0.1", "5");

      TemplateOptions options = ipsToNetworks(ipsToNetworks);
      assertEquals(options.as(AliyunTemplateOptions.class)
         .getIpsToNetworks().get("10.0.0.1"), "5");
   }

   @Test
   public void testSetupStaticNatDefaultsTrue() {
      TemplateOptions options = new AliyunTemplateOptions();
      assertTrue(options.as(AliyunTemplateOptions.class)
         .shouldSetupStaticNat());
   }

   @Test
   public void testSetupStaticNat() {
      TemplateOptions options = new AliyunTemplateOptions().setupStaticNat(false);
      assertFalse(options.as(AliyunTemplateOptions.class)
         .shouldSetupStaticNat());
   }

   @Test
   public void testSetupStaticNatStatic() {
      TemplateOptions options = setupStaticNat(false);
      assertFalse(options.as(AliyunTemplateOptions.class)
         .shouldSetupStaticNat());
   }

   @Test
   public void testGenerateKeyPairDefaultsFalse() {
      TemplateOptions options = new AliyunTemplateOptions();
      assertFalse(options.as(AliyunTemplateOptions.class)
         .shouldGenerateKeyPair());
   }

   @Test
   public void testGenerateKeyPair() {
      TemplateOptions options = new AliyunTemplateOptions().generateKeyPair(true);
      assertTrue(options.as(AliyunTemplateOptions.class)
         .shouldGenerateKeyPair());
   }

   @Test
   public void testGenerateKeyPairStatic() {
      TemplateOptions options = generateKeyPair(true);
      assertTrue(options.as(AliyunTemplateOptions.class)
         .shouldGenerateKeyPair());
   }

   @Test
   public void testKeyPair() {
      TemplateOptions options = keyPair("test");
      assertEquals(options.as(AliyunTemplateOptions.class).getKeyPair(), "test");
   }

   @Test
   public void testDiskOfferingId() {
      TemplateOptions options = diskOfferingId("test");
      assertEquals(options.as(AliyunTemplateOptions.class).getDiskOfferingId(), "test");
   }

   @Test
   public void testDataDiskSizeDefault() {
      TemplateOptions options = new AliyunTemplateOptions();
      assertEquals(options.as(AliyunTemplateOptions.class).getDataDiskSize(), 0);
   }

   @Test
   public void testDataDiskSize() {
      TemplateOptions options = dataDiskSize(10);
      assertEquals(options.as(AliyunTemplateOptions.class).getDataDiskSize(), 10);
   }

   @Test
   public void testAccount() {
      TemplateOptions options = account("test");
      assertEquals(options.as(AliyunTemplateOptions.class).getAccount(), "test");
   }

   @Test
   public void testDomainId() {
      TemplateOptions options = domainId("test");
      assertEquals(options.as(AliyunTemplateOptions.class).getDomainId(), "test");
   }

   @Test
   public void testSecurityGroupIdsNullHasDecentMessage() {
      try {
         new AliyunTemplateOptions().securityGroupIds(null);
         fail("should NPE");
      } catch (NullPointerException e) {
         assertEquals(e.getMessage(), "securityGroupIds was null");
      }
   }

}
