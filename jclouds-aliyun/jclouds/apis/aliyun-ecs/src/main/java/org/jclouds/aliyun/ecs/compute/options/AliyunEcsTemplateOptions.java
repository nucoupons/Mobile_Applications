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
package org.jclouds.aliyun.ecs.compute.options;

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
 * {@link AliyunEcsTemplateOptions} object is to statically import
 * {@code CloudStackTemplateOptions.*} and invoke a static creation method
 * followed by an instance mutator (if needed):
 * <p>
 *
 * <pre>
 * import static org.jclouds.compute.options.CloudStackTemplateOptions.Builder.*;
 * ComputeService client = // get connection
 * templateBuilder.options(inboundPorts(22, 80, 8080, 443));
 * Set&lt;? extends NodeMetadata&gt; set = client.createNodesInGroup(tag, 2, templateBuilder.build());
 * </pre>
 */
public class AliyunEcsTemplateOptions extends TemplateOptions implements Cloneable {

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
   protected byte[] unencodedData;

   @Override
   public AliyunEcsTemplateOptions clone() {
      AliyunEcsTemplateOptions options = new AliyunEcsTemplateOptions();
      copyTo(options);
      return options;
   }

   @Override
   public void copyTo(TemplateOptions to) {
      super.copyTo(to);
      if (to instanceof AliyunEcsTemplateOptions) {
         AliyunEcsTemplateOptions eTo = AliyunEcsTemplateOptions.class.cast(to);
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
         eTo.userData(unencodedData);
      }
   }

   /**
    * @see org.jclouds.cloudstack.options.DeployVirtualMachineOptions#diskOfferingId
    */
   public AliyunEcsTemplateOptions diskOfferingId(String diskOfferingId) {
      this.diskOfferingId = diskOfferingId;
      return this;
   }

   public String getDiskOfferingId() {
      return diskOfferingId;
   }

   /**
    * @see DeployVirtualMachineOptions#dataDiskSize
    */
   public AliyunEcsTemplateOptions dataDiskSize(int dataDiskSize) {
      this.dataDiskSize = dataDiskSize;
      return this;
   }

   public int getDataDiskSize() {
      return dataDiskSize;
   }

   /**
    * @see DeployVirtualMachineOptions#userData
    */
   public AliyunEcsTemplateOptions userData(byte[] unencodedData) {
      this.unencodedData = unencodedData;
      return this;
   }

   public byte[] getUserData() {
      return unencodedData;
   }

   /**
    * @see DeployVirtualMachineOptions#securityGroupId
    */
   public AliyunEcsTemplateOptions securityGroupId(String securityGroupId) {
      this.securityGroupIds.add(securityGroupId);
      return this;
   }

   /**
    * @see DeployVirtualMachineOptions#securityGroupIds
    */
   public AliyunEcsTemplateOptions securityGroupIds(Iterable<String> securityGroupIds) {
      Iterables.addAll(this.securityGroupIds, checkNotNull(securityGroupIds, "securityGroupIds was null"));
      return this;
   }

   public Set<String> getSecurityGroupIds() {
      return securityGroupIds;
   }

   /**
    * @see #shouldGenerateKeyPair()
    */
   public AliyunEcsTemplateOptions generateSecurityGroup(boolean enable) {
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
   public AliyunEcsTemplateOptions networkId(String networkId) {
      this.networks.add(networkId);
      return this;
   }

   /**
    * @deprecated See TemplateOptions#networks
    * @see DeployVirtualMachineOptions#networkIds
    */
   @Deprecated
   public AliyunEcsTemplateOptions networkIds(Iterable<String> networkIds) {
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

   public AliyunEcsTemplateOptions setupStaticNat(boolean setupStaticNat) {
      this.setupStaticNat = setupStaticNat;
      return this;
   }

   public boolean shouldSetupStaticNat() {
      return this.setupStaticNat;
   }

   /**
    * @see DeployVirtualMachineOptions#ipOnDefaultNetwork
    */
   public AliyunEcsTemplateOptions ipOnDefaultNetwork(String ipOnDefaultNetwork) {
      this.ipOnDefaultNetwork = ipOnDefaultNetwork;
      return this;
   }

   public String getIpOnDefaultNetwork() {
      return ipOnDefaultNetwork;
   }

   /**
    * @see DeployVirtualMachineOptions#ipOnDefaultNetwork(String)
    */
   public AliyunEcsTemplateOptions ipsToNetworks(Map<String, String> ipsToNetworks) {
      this.ipsToNetworks.putAll(ipsToNetworks);
      return this;
   }

   public Map<String, String> getIpsToNetworks() {
      return ipsToNetworks;
   }

   /**
    * @see DeployVirtualMachineOptions#keyPair(String)
    */
   public AliyunEcsTemplateOptions keyPair(String keyPair) {
      this.keyPair = keyPair;
      return this;
   }

   public String getKeyPair() {
      return keyPair;
   }

   /**
    * @see #shouldGenerateKeyPair()
    */
   public AliyunEcsTemplateOptions generateKeyPair(boolean enable) {
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
   public AliyunEcsTemplateOptions account(String account) {
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
   public AliyunEcsTemplateOptions domainId(String domainId) {
      this.domainId = domainId;
      return this;
   }

   public String getDomainId() {
      return domainId;
   }

   public static final AliyunEcsTemplateOptions NONE = new AliyunEcsTemplateOptions();

   public static class Builder {

      /**
       * @see AliyunEcsTemplateOptions#diskOfferingId
       */
      public static AliyunEcsTemplateOptions diskOfferingId(String diskOfferingId) {
         AliyunEcsTemplateOptions options = new AliyunEcsTemplateOptions();
         return options.diskOfferingId(diskOfferingId);
      }

      /**
       * @see AliyunEcsTemplateOptions#dataDiskSize
       */
      public static AliyunEcsTemplateOptions dataDiskSize(int dataDiskSize) {
         AliyunEcsTemplateOptions options = new AliyunEcsTemplateOptions();
         return options.dataDiskSize(dataDiskSize);
      }

      /**
       * @see AliyunEcsTemplateOptions#userData
       */
      public static AliyunEcsTemplateOptions userData(byte[] unencodedData) {
         AliyunEcsTemplateOptions options = new AliyunEcsTemplateOptions();
         return options.userData(unencodedData);
      }

      /**
       * @see AliyunEcsTemplateOptions#securityGroupId
       */
      public static AliyunEcsTemplateOptions securityGroupId(String id) {
         AliyunEcsTemplateOptions options = new AliyunEcsTemplateOptions();
         return options.securityGroupId(id);
      }

      /**
       * @see AliyunEcsTemplateOptions#securityGroupIds
       */
      public static AliyunEcsTemplateOptions securityGroupIds(Iterable<String> securityGroupIds) {
         AliyunEcsTemplateOptions options = new AliyunEcsTemplateOptions();
         return options.securityGroupIds(securityGroupIds);
      }

      /**
       * @see AliyunEcsTemplateOptions#shouldGenerateSecurityGroup()
       */
      public static AliyunEcsTemplateOptions generateSecurityGroup(boolean enable) {
         return new AliyunEcsTemplateOptions().generateSecurityGroup(enable);
      }

      /**
       * @deprecated See TemplateOptions#networks
       * @see AliyunEcsTemplateOptions#networkId
       */
      @Deprecated
      public static AliyunEcsTemplateOptions networkId(String id) {
         return networks(id);
      }

      /**
       * @deprecated see TemplateOptions#networks
       * @see AliyunEcsTemplateOptions#networkIds
       */
      @Deprecated
      public static AliyunEcsTemplateOptions networkIds(Iterable<String> networkIds) {
         return networks(networkIds);
      }

      /**
       * @see AliyunEcsTemplateOptions#ipOnDefaultNetwork
       */
      public static AliyunEcsTemplateOptions ipOnDefaultNetwork(String ipAddress) {
         AliyunEcsTemplateOptions options = new AliyunEcsTemplateOptions();
         return options.ipOnDefaultNetwork(ipAddress);
      }

      /**
       * @see AliyunEcsTemplateOptions#ipsToNetworks
       */
      public static AliyunEcsTemplateOptions ipsToNetworks(Map<String, String> ipToNetworkMap) {
         AliyunEcsTemplateOptions options = new AliyunEcsTemplateOptions();
         return options.ipsToNetworks(ipToNetworkMap);
      }

      /**
       * @see AliyunEcsTemplateOptions#setupStaticNat
       */
      public static AliyunEcsTemplateOptions setupStaticNat(boolean setupStaticNat) {
         AliyunEcsTemplateOptions options = new AliyunEcsTemplateOptions();
         return options.setupStaticNat(setupStaticNat);
      }

      /**
       * @see AliyunEcsTemplateOptions#keyPair
       */
      public static AliyunEcsTemplateOptions keyPair(String keyPair) {
         AliyunEcsTemplateOptions options = new AliyunEcsTemplateOptions();
         return options.keyPair(keyPair);
      }

      /**
       * @see AliyunEcsTemplateOptions#shouldGenerateKeyPair()
       */
      public static AliyunEcsTemplateOptions generateKeyPair(boolean enable) {
         return new AliyunEcsTemplateOptions().generateKeyPair(enable);
      }

      /**
       * @see AliyunEcsTemplateOptions#account
       */
      public static AliyunEcsTemplateOptions account(String account) {
         AliyunEcsTemplateOptions options = new AliyunEcsTemplateOptions();
         return options.account(account);
      }

      /**
       * @see AliyunEcsTemplateOptions#domainId
       */
      public static AliyunEcsTemplateOptions domainId(String domainId) {
         AliyunEcsTemplateOptions options = new AliyunEcsTemplateOptions();
         return options.domainId(domainId);
      }

      // methods that only facilitate returning the correct object type

      /**
       * @see TemplateOptions#inboundPorts(int...)
       */
      public static AliyunEcsTemplateOptions inboundPorts(int... ports) {
         AliyunEcsTemplateOptions options = new AliyunEcsTemplateOptions();
         return AliyunEcsTemplateOptions.class.cast(options.inboundPorts(ports));
      }

      /**
       * @see TemplateOptions#blockOnPort(int, int)
       */
      public static AliyunEcsTemplateOptions blockOnPort(int port, int seconds) {
         AliyunEcsTemplateOptions options = new AliyunEcsTemplateOptions();
         return AliyunEcsTemplateOptions.class.cast(options.blockOnPort(port, seconds));
      }

      /**
       * @see TemplateOptions#userMetadata(Map)
       */
      public static AliyunEcsTemplateOptions userMetadata(Map<String, String> userMetadata) {
         AliyunEcsTemplateOptions options = new AliyunEcsTemplateOptions();
         return AliyunEcsTemplateOptions.class.cast(options.userMetadata(userMetadata));
      }

      /**
       * @see TemplateOptions#userMetadata(String, String)
       */
      public static AliyunEcsTemplateOptions userMetadata(String key, String value) {
         AliyunEcsTemplateOptions options = new AliyunEcsTemplateOptions();
         return AliyunEcsTemplateOptions.class.cast(options.userMetadata(key, value));
      }

      /**
       * @see TemplateOptions#nodeNames(Iterable)
       */
      public static AliyunEcsTemplateOptions nodeNames(Iterable<String> nodeNames) {
         AliyunEcsTemplateOptions options = new AliyunEcsTemplateOptions();
         return AliyunEcsTemplateOptions.class.cast(options.nodeNames(nodeNames));
      }

      /**
       * @see TemplateOptions#networks(Iterable)
       */
      public static AliyunEcsTemplateOptions networks(Iterable<String> networks) {
         AliyunEcsTemplateOptions options = new AliyunEcsTemplateOptions();
         return AliyunEcsTemplateOptions.class.cast(options.networks(networks));
      }

      /**
       * @see TemplateOptions#networks(String...)
       */
      public static AliyunEcsTemplateOptions networks(String... networks) {
         AliyunEcsTemplateOptions options = new AliyunEcsTemplateOptions();
         return AliyunEcsTemplateOptions.class.cast(options.networks(networks));
      }
   }

   // methods that only facilitate returning the correct object type

   /**
    * @see TemplateOptions#blockOnPort(int, int)
    */
   @Override
   public AliyunEcsTemplateOptions blockOnPort(int port, int seconds) {
      return AliyunEcsTemplateOptions.class.cast(super.blockOnPort(port, seconds));
   }

   /**
    * @see TemplateOptions#inboundPorts(int...)
    */
   @Override
   public AliyunEcsTemplateOptions inboundPorts(int... ports) {
      return AliyunEcsTemplateOptions.class.cast(super.inboundPorts(ports));
   }

   /**
    * @see TemplateOptions#authorizePublicKey(String)
    */
   @Override
   public AliyunEcsTemplateOptions authorizePublicKey(String publicKey) {
      return AliyunEcsTemplateOptions.class.cast(super.authorizePublicKey(publicKey));
   }

   /**
    * @see TemplateOptions#installPrivateKey(String)
    */
   @Override
   public AliyunEcsTemplateOptions installPrivateKey(String privateKey) {
      return AliyunEcsTemplateOptions.class.cast(super.installPrivateKey(privateKey));
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public AliyunEcsTemplateOptions userMetadata(Map<String, String> userMetadata) {
      return AliyunEcsTemplateOptions.class.cast(super.userMetadata(userMetadata));
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public AliyunEcsTemplateOptions userMetadata(String key, String value) {
      return AliyunEcsTemplateOptions.class.cast(super.userMetadata(key, value));
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public AliyunEcsTemplateOptions nodeNames(Iterable<String> nodeNames) {
      return AliyunEcsTemplateOptions.class.cast(super.nodeNames(nodeNames));
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public AliyunEcsTemplateOptions networks(Iterable<String> networks) {
      return AliyunEcsTemplateOptions.class.cast(super.networks(networks));
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public AliyunEcsTemplateOptions networks(String... networks) {
      return AliyunEcsTemplateOptions.class.cast(super.networks(networks));
   }
}

