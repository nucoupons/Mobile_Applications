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

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Map;
import java.util.Set;

import org.jclouds.compute.options.TemplateOptions;

import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

/**
 * Contains options supported by the
 * {@link ComputeService#createNodesInGroup(String, int, TemplateOptions)} and
 * {@link ComputeService#createNodesInGroup(String, int, TemplateOptions)}
 * operations on the <em>gogrid</em> provider.
 * 
 * <h2>Usage</h2> The recommended way to instantiate a
 * {@link AliyunTemplateOptions} object is to statically import
 * {@code AliyunTemplateOptions.*} and invoke a static creation method
 * followed by an instance mutator (if needed):
 * <p>
 * 
 * <pre>
 * import static org.jclouds.compute.options.AliyunTemplateOptions.Builder.*;
 * ComputeService client = // get connection
 * templateBuilder.options(inboundPorts(22, 80, 8080, 443));
 * Set&lt;? extends NodeMetadata&gt; set = client.createNodesInGroup(tag, 2, templateBuilder.build());
 * </pre>
 */
public class AliyunTemplateOptions extends TemplateOptions implements Cloneable {

   protected Set<String> securityGroupIds = Sets.<String> newLinkedHashSet();
   protected Map<String, String> ipsToNetworks = Maps.<String, String>newLinkedHashMap();
   protected String ipOnDefaultNetwork;
   protected String keyPair;
   protected boolean setupStaticNat = true;
   protected String account;
   protected String domainId;
   protected boolean generateKeyPair = false;
   protected boolean generateSecurityGroup = false;
   protected String diskOfferingId;
   protected int dataDiskSize;
   
   @Override
   public AliyunTemplateOptions clone() {
      AliyunTemplateOptions options = new AliyunTemplateOptions();
      copyTo(options);
      return options;
   }

   @Override
   public void copyTo(TemplateOptions to) {
      super.copyTo(to);
      if (to instanceof AliyunTemplateOptions) {
         AliyunTemplateOptions eTo = AliyunTemplateOptions.class.cast(to);
         eTo.securityGroupIds(this.securityGroupIds);
         eTo.ipsToNetworks(this.ipsToNetworks);
         eTo.ipOnDefaultNetwork(this.ipOnDefaultNetwork);
         eTo.keyPair(this.keyPair);
         eTo.generateKeyPair(shouldGenerateKeyPair());
         eTo.generateSecurityGroup(shouldGenerateSecurityGroup());
         eTo.account(this.account);
         eTo.domainId(this.domainId);
         eTo.setupStaticNat(setupStaticNat);
         eTo.diskOfferingId(diskOfferingId);
         eTo.dataDiskSize(dataDiskSize);
      }
   }

   /**
    * @see org.jclouds.aliyun.options.DeployVirtualMachineOptions#diskOfferingId
    */
   public AliyunTemplateOptions diskOfferingId(String diskOfferingId) {
      this.diskOfferingId = diskOfferingId;
      return this;
   }

   public String getDiskOfferingId() {
      return diskOfferingId;
   }

   /**
    * @see DeployVirtualMachineOptions#dataDiskSize
    */
   public AliyunTemplateOptions dataDiskSize(int dataDiskSize) {
      this.dataDiskSize = dataDiskSize;
      return this;
   }

   public int getDataDiskSize() {
      return dataDiskSize;
   }

   /**
    * @see DeployVirtualMachineOptions#securityGroupId
    */
   public AliyunTemplateOptions securityGroupId(String securityGroupId) {
      this.securityGroupIds.add(securityGroupId);
      return this;
   }

   /**
    * @see DeployVirtualMachineOptions#securityGroupIds
    */
   public AliyunTemplateOptions securityGroupIds(Iterable<String> securityGroupIds) {
      Iterables.addAll(this.securityGroupIds, checkNotNull(securityGroupIds, "securityGroupIds was null"));
      return this;
   }

   public Set<String> getSecurityGroupIds() {
      return securityGroupIds;
   }

   /**
    * @see #shouldGenerateKeyPair()
    */
   public AliyunTemplateOptions generateSecurityGroup(boolean enable) {
      this.generateSecurityGroup = enable;
      return this;
   }

   /**
    * @return true if auto generation of keypairs is enabled
    */
   public boolean shouldGenerateSecurityGroup() {
      return generateSecurityGroup;
   }

   /**
    * @deprecated See TemplateOptions#networks
    * @see DeployVirtualMachineOptions#networkId
    */
   @Deprecated
   public AliyunTemplateOptions networkId(String networkId) {
      this.networks.add(networkId);
      return this;
   }

   /**
    * @deprecated See TemplateOptions#networks
    * @see DeployVirtualMachineOptions#networkIds
    */
   @Deprecated
   public AliyunTemplateOptions networkIds(Iterable<String> networkIds) {
      Iterables.addAll(this.networks, checkNotNull(networkIds, "networkIds was null"));
      return this;
   }

   /**
    * @deprecated See TemplateOptions#getNetworks
    */
   @Deprecated
   public Set<String> getNetworkIds() {
      return this.getNetworks();
   }

   public AliyunTemplateOptions setupStaticNat(boolean setupStaticNat) {
      this.setupStaticNat = setupStaticNat;
      return this;
   }

   public boolean shouldSetupStaticNat() {
      return this.setupStaticNat;
   }

   /**
    * @see DeployVirtualMachineOptions#ipOnDefaultNetwork
    */
   public AliyunTemplateOptions ipOnDefaultNetwork(String ipOnDefaultNetwork) {
      this.ipOnDefaultNetwork = ipOnDefaultNetwork;
      return this;
   }

   public String getIpOnDefaultNetwork() {
      return ipOnDefaultNetwork;
   }

   /**
    * @see DeployVirtualMachineOptions#ipOnDefaultNetwork(String)
    */
   public AliyunTemplateOptions ipsToNetworks(Map<String, String> ipsToNetworks) {
      this.ipsToNetworks.putAll(ipsToNetworks);
      return this;
   }

   public Map<String, String> getIpsToNetworks() {
      return ipsToNetworks;
   }

   /**
    * @see DeployVirtualMachineOptions#keyPair(String)
    */
   public AliyunTemplateOptions keyPair(String keyPair) {
      this.keyPair = keyPair;
      return this;
   }

   public String getKeyPair() {
      return keyPair;
   }

   /**
    * @see #shouldGenerateKeyPair()
    */
   public AliyunTemplateOptions generateKeyPair(boolean enable) {
      this.generateKeyPair = enable;
      return this;
   }

   /**
    * @return true if auto generation of keypairs is enabled
    */
   public boolean shouldGenerateKeyPair() {
      return generateKeyPair;
   }

   /**
    * @see DeployVirtualMachineOptions#accountInDomain(String,String)
    */
   public AliyunTemplateOptions account(String account) {
      this.account = account;
      return this;
   }

   public String getAccount() {
      return account;
   }

   /**
    * @see DeployVirtualMachineOptions#accountInDomain(String,String)
    * @see DeployVirtualMachineOptions#domainId(String)
    */
   public AliyunTemplateOptions domainId(String domainId) {
      this.domainId = domainId;
      return this;
   }

   public String getDomainId() {
      return domainId;
   }

   public static final AliyunTemplateOptions NONE = new AliyunTemplateOptions();

   public static class Builder {

      /**
       * @see AliyunTemplateOptions#diskOfferingId
       */
      public static AliyunTemplateOptions diskOfferingId(String diskOfferingId) {
         AliyunTemplateOptions options = new AliyunTemplateOptions();
         return options.diskOfferingId(diskOfferingId);
      }

      /**
       * @see AliyunTemplateOptions#dataDiskSize
       */
      public static AliyunTemplateOptions dataDiskSize(int dataDiskSize) {
         AliyunTemplateOptions options = new AliyunTemplateOptions();
         return options.dataDiskSize(dataDiskSize);
      }

      /**
       * @see AliyunTemplateOptions#securityGroupId
       */
      public static AliyunTemplateOptions securityGroupId(String id) {
         AliyunTemplateOptions options = new AliyunTemplateOptions();
         return options.securityGroupId(id);
      }

      /**
       * @see AliyunTemplateOptions#securityGroupIds
       */
      public static AliyunTemplateOptions securityGroupIds(Iterable<String> securityGroupIds) {
         AliyunTemplateOptions options = new AliyunTemplateOptions();
         return options.securityGroupIds(securityGroupIds);
      }

      /**
       * @see AliyunTemplateOptions#shouldGenerateSecurityGroup() 
       */
      public static AliyunTemplateOptions generateSecurityGroup(boolean enable) {
         return new AliyunTemplateOptions().generateSecurityGroup(enable);
      }

      /**
       * @deprecated See TemplateOptions#networks
       * @see AliyunTemplateOptions#networkId
       */
      @Deprecated
      public static AliyunTemplateOptions networkId(String id) {
         return networks(id);
      }

      /**
       * @deprecated see TemplateOptions#networks
       * @see AliyunTemplateOptions#networkIds
       */
      @Deprecated
      public static AliyunTemplateOptions networkIds(Iterable<String> networkIds) {
         return networks(networkIds);
      }

      /**
       * @see AliyunTemplateOptions#ipOnDefaultNetwork
       */
      public static AliyunTemplateOptions ipOnDefaultNetwork(String ipAddress) {
         AliyunTemplateOptions options = new AliyunTemplateOptions();
         return options.ipOnDefaultNetwork(ipAddress);
      }

      /**
       * @see AliyunTemplateOptions#ipsToNetworks
       */
      public static AliyunTemplateOptions ipsToNetworks(Map<String, String> ipToNetworkMap) {
         AliyunTemplateOptions options = new AliyunTemplateOptions();
         return options.ipsToNetworks(ipToNetworkMap);
      }

      /**
       * @see AliyunTemplateOptions#setupStaticNat
       */
      public static AliyunTemplateOptions setupStaticNat(boolean setupStaticNat) {
         AliyunTemplateOptions options = new AliyunTemplateOptions();
         return options.setupStaticNat(setupStaticNat);
      }

      /**
       * @see AliyunTemplateOptions#keyPair
       */
      public static AliyunTemplateOptions keyPair(String keyPair) {
         AliyunTemplateOptions options = new AliyunTemplateOptions();
         return options.keyPair(keyPair);
      }

      /**
       * @see AliyunTemplateOptions#shouldGenerateKeyPair() 
       */
      public static AliyunTemplateOptions generateKeyPair(boolean enable) {
         return new AliyunTemplateOptions().generateKeyPair(enable);
      }

      /**
       * @see AliyunTemplateOptions#account
       */
      public static AliyunTemplateOptions account(String account) {
         AliyunTemplateOptions options = new AliyunTemplateOptions();
         return options.account(account);
      }

      /**
       * @see AliyunTemplateOptions#domainId
       */
      public static AliyunTemplateOptions domainId(String domainId) {
         AliyunTemplateOptions options = new AliyunTemplateOptions();
         return options.domainId(domainId);
      }

      // methods that only facilitate returning the correct object type

      /**
       * @see TemplateOptions#inboundPorts(int...)
       */
      public static AliyunTemplateOptions inboundPorts(int... ports) {
         AliyunTemplateOptions options = new AliyunTemplateOptions();
         return AliyunTemplateOptions.class.cast(options.inboundPorts(ports));
      }

      /**
       * @see TemplateOptions#blockOnPort(int, int)
       */
      public static AliyunTemplateOptions blockOnPort(int port, int seconds) {
         AliyunTemplateOptions options = new AliyunTemplateOptions();
         return AliyunTemplateOptions.class.cast(options.blockOnPort(port, seconds));
      }

      /**
       * @see TemplateOptions#userMetadata(Map)
       */
      public static AliyunTemplateOptions userMetadata(Map<String, String> userMetadata) {
         AliyunTemplateOptions options = new AliyunTemplateOptions();
         return AliyunTemplateOptions.class.cast(options.userMetadata(userMetadata));
      }

      /**
       * @see TemplateOptions#userMetadata(String, String)
       */
      public static AliyunTemplateOptions userMetadata(String key, String value) {
         AliyunTemplateOptions options = new AliyunTemplateOptions();
         return AliyunTemplateOptions.class.cast(options.userMetadata(key, value));
      }

      /**
       * @see TemplateOptions#nodeNames(Iterable)
       */
      public static AliyunTemplateOptions nodeNames(Iterable<String> nodeNames) {
         AliyunTemplateOptions options = new AliyunTemplateOptions();
         return AliyunTemplateOptions.class.cast(options.nodeNames(nodeNames));
      }

      /**
       * @see TemplateOptions#networks(Iterable)
       */
      public static AliyunTemplateOptions networks(Iterable<String> networks) {
         AliyunTemplateOptions options = new AliyunTemplateOptions();
         return AliyunTemplateOptions.class.cast(options.networks(networks));
      }

      /**
       * @see TemplateOptions#networks(String...)
       */
      public static AliyunTemplateOptions networks(String... networks) {
         AliyunTemplateOptions options = new AliyunTemplateOptions();
         return AliyunTemplateOptions.class.cast(options.networks(networks));
      }
   }

   // methods that only facilitate returning the correct object type

   /**
    * @see TemplateOptions#blockOnPort(int, int)
    */
   @Override
   public AliyunTemplateOptions blockOnPort(int port, int seconds) {
      return AliyunTemplateOptions.class.cast(super.blockOnPort(port, seconds));
   }

   /**
    * @see TemplateOptions#inboundPorts(int...)
    */
   @Override
   public AliyunTemplateOptions inboundPorts(int... ports) {
      return AliyunTemplateOptions.class.cast(super.inboundPorts(ports));
   }

   /**
    * @see TemplateOptions#authorizePublicKey(String)
    */
   @Override
   public AliyunTemplateOptions authorizePublicKey(String publicKey) {
      return AliyunTemplateOptions.class.cast(super.authorizePublicKey(publicKey));
   }

   /**
    * @see TemplateOptions#installPrivateKey(String)
    */
   @Override
   public AliyunTemplateOptions installPrivateKey(String privateKey) {
      return AliyunTemplateOptions.class.cast(super.installPrivateKey(privateKey));
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public AliyunTemplateOptions userMetadata(Map<String, String> userMetadata) {
      return AliyunTemplateOptions.class.cast(super.userMetadata(userMetadata));
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public AliyunTemplateOptions userMetadata(String key, String value) {
      return AliyunTemplateOptions.class.cast(super.userMetadata(key, value));
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public AliyunTemplateOptions nodeNames(Iterable<String> nodeNames) {
      return AliyunTemplateOptions.class.cast(super.nodeNames(nodeNames));
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public AliyunTemplateOptions networks(Iterable<String> networks) {
      return AliyunTemplateOptions.class.cast(super.networks(networks));
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public AliyunTemplateOptions networks(String... networks) {
      return AliyunTemplateOptions.class.cast(super.networks(networks));
   }
}
