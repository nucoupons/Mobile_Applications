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

import java.io.Closeable;

import org.jclouds.aliyun.features.AccountApi;
import org.jclouds.aliyun.features.AddressApi;
import org.jclouds.aliyun.features.AsyncJobApi;
import org.jclouds.aliyun.features.ConfigurationApi;
import org.jclouds.aliyun.features.EventApi;
import org.jclouds.aliyun.features.FirewallApi;
import org.jclouds.aliyun.features.GuestOSApi;
import org.jclouds.aliyun.features.HypervisorApi;
import org.jclouds.aliyun.features.ISOApi;
import org.jclouds.aliyun.features.LimitApi;
import org.jclouds.aliyun.features.LoadBalancerApi;
import org.jclouds.aliyun.features.NATApi;
import org.jclouds.aliyun.features.NetworkApi;
import org.jclouds.aliyun.features.OfferingApi;
import org.jclouds.aliyun.features.ProjectApi;
import org.jclouds.aliyun.features.SSHKeyPairApi;
import org.jclouds.aliyun.features.SecurityGroupApi;
import org.jclouds.aliyun.features.SessionApi;
import org.jclouds.aliyun.features.SnapshotApi;
import org.jclouds.aliyun.features.TagApi;
import org.jclouds.aliyun.features.TemplateApi;
import org.jclouds.aliyun.features.VMGroupApi;
import org.jclouds.aliyun.features.VirtualMachineApi;
import org.jclouds.aliyun.features.VolumeApi;
import org.jclouds.aliyun.features.ZoneApi;
import org.jclouds.rest.annotations.Delegate;

/**
 * Provides synchronous access to CloudStack.
 * <p/>
 *
 * @see <a href="http://download.cloud.com/releases/2.2.0/api_2.2.12/TOC_User.html" />
 */
public interface AliyunApi extends Closeable {
   /**
    * Provides synchronous access to Zone features.
    */
   @Delegate
   ZoneApi getZoneApi();

   /**
    * Provides synchronous access to Template features.
    */
   @Delegate
   TemplateApi getTemplateApi();

   /**
    * Provides synchronous access to Service, Disk, and Network Offering
    * features.
    */
   @Delegate
   OfferingApi getOfferingApi();

   /**
    * Provides synchronous access to Network features.
    */
   @Delegate
   NetworkApi getNetworkApi();

   /**
    * Provides synchronous access to VirtualMachine features.
    */
   @Delegate
   VirtualMachineApi getVirtualMachineApi();

   /**
    * Provides synchronous access to SecurityGroup features.
    */
   @Delegate
   SecurityGroupApi getSecurityGroupApi();

   /**
    * Provides synchronous access to AsyncJob features.
    */
   @Delegate
   AsyncJobApi getAsyncJobApi();

   /**
    * Provides synchronous access to Address features.
    */
   @Delegate
   AddressApi getAddressApi();

   /**
    * Provides synchronous access to NAT features.
    */
   @Delegate
   NATApi getNATApi();

   /**
    * Provides synchronous access to Firewall features.
    */
   @Delegate
   FirewallApi getFirewallApi();

   /**
    * Provides synchronous access to LoadBalancer features.
    */
   @Delegate
   LoadBalancerApi getLoadBalancerApi();

   /**
    * Provides synchronous access to GuestOS features.
    */
   @Delegate
   GuestOSApi getGuestOSApi();

   /**
    * Provides synchronous access to Hypervisor features.
    */
   @Delegate
   HypervisorApi getHypervisorApi();

   /**
    * Provides synchronous access to Configuration features.
    */
   @Delegate
   ConfigurationApi getConfigurationApi();

   /**
    * Provides synchronous access to Account features.
    */
   @Delegate
   AccountApi getAccountApi();

   /**
    * Provides synchronous access to SSH Keypairs
    */
   @Delegate
   SSHKeyPairApi getSSHKeyPairApi();

   /**
    * Provides synchronous access to VM groups
    */
   @Delegate
   VMGroupApi getVMGroupApi();

   /**
    * Provides synchronous access to Events
    */
   @Delegate
   EventApi getEventApi();

   /**
    * Provides synchronous access to Resource Limits
    */
   @Delegate
   LimitApi getLimitApi();

   /**
    * Provides synchronous access to ISOs
    */
   @Delegate
   ISOApi getISOApi();

   /**
    * Provides synchronous access to Volumes
    */
   @Delegate
   VolumeApi getVolumeApi();

   /**
    * Provides synchronous access to Snapshots
    */
   @Delegate
   SnapshotApi getSnapshotApi();

   /**
    * Provides synchronous access to Sessions
    */
   @Delegate
   SessionApi getSessionApi();

   /**
    * Provides synchronous access to Projects
    */
   @Delegate
   ProjectApi getProjectApi();

   /**
    * Provides synchronous access to Tags
    */
   @Delegate
   TagApi getTagApi();
}
