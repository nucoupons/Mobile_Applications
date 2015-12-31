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
package org.jclouds.aliyun.compute.config;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.jclouds.Constants.PROPERTY_SESSION_INTERVAL;
import static org.jclouds.aliyun.config.AliyunProperties.AUTO_GENERATE_KEYPAIRS;
import static org.jclouds.util.Predicates2.retry;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import javax.inject.Named;
import javax.inject.Singleton;

import org.jclouds.aliyun.AliyunApi;
import org.jclouds.aliyun.compute.extensions.AliyunImageExtension;
import org.jclouds.aliyun.compute.extensions.AliyunSecurityGroupExtension;
import org.jclouds.aliyun.compute.functions.AliyunSecurityGroupToSecurityGroup;
import org.jclouds.aliyun.compute.functions.IngressRuleToIpPermission;
import org.jclouds.aliyun.compute.functions.OrphanedGroupsByZoneId;
import org.jclouds.aliyun.compute.functions.ServiceOfferingToHardware;
import org.jclouds.aliyun.compute.functions.TemplateToImage;
import org.jclouds.aliyun.compute.functions.TemplateToOperatingSystem;
import org.jclouds.aliyun.compute.functions.VirtualMachineToNodeMetadata;
import org.jclouds.aliyun.compute.functions.ZoneToLocation;
import org.jclouds.aliyun.compute.loaders.CreateUniqueKeyPair;
import org.jclouds.aliyun.compute.loaders.FindSecurityGroupOrCreate;
import org.jclouds.aliyun.compute.options.AliyunTemplateOptions;
import org.jclouds.aliyun.compute.strategy.AdvancedNetworkOptionsConverter;
import org.jclouds.aliyun.compute.strategy.BasicNetworkOptionsConverter;
import org.jclouds.aliyun.compute.strategy.AliyunComputeServiceAdapter;
import org.jclouds.aliyun.compute.strategy.OptionsConverter;
import org.jclouds.aliyun.domain.FirewallRule;
import org.jclouds.aliyun.domain.IPForwardingRule;
import org.jclouds.aliyun.domain.IngressRule;
import org.jclouds.aliyun.domain.Network;
import org.jclouds.aliyun.domain.NetworkType;
import org.jclouds.aliyun.domain.OSType;
import org.jclouds.aliyun.domain.Project;
import org.jclouds.aliyun.domain.SecurityGroup;
import org.jclouds.aliyun.domain.ServiceOffering;
import org.jclouds.aliyun.domain.SshKeyPair;
import org.jclouds.aliyun.domain.Template;
import org.jclouds.aliyun.domain.User;
import org.jclouds.aliyun.domain.VirtualMachine;
import org.jclouds.aliyun.domain.Zone;
import org.jclouds.aliyun.domain.ZoneAndName;
import org.jclouds.aliyun.domain.ZoneSecurityGroupNamePortsCidrs;
import org.jclouds.aliyun.features.GuestOSApi;
import org.jclouds.aliyun.functions.CreateSecurityGroupIfNeeded;
import org.jclouds.aliyun.functions.GetFirewallRulesByVirtualMachine;
import org.jclouds.aliyun.functions.GetIPForwardingRulesByVirtualMachine;
import org.jclouds.aliyun.functions.StaticNATVirtualMachineInNetwork;
import org.jclouds.aliyun.functions.ZoneIdToZone;
import org.jclouds.aliyun.predicates.JobComplete;
import org.jclouds.aliyun.suppliers.GetCurrentUser;
import org.jclouds.aliyun.suppliers.NetworksForCurrentUser;
import org.jclouds.aliyun.suppliers.ProjectsForCurrentUser;
import org.jclouds.aliyun.suppliers.ZoneIdToZoneSupplier;
import org.jclouds.collect.Memoized;
import org.jclouds.compute.ComputeServiceAdapter;
import org.jclouds.compute.config.ComputeServiceAdapterContextModule;
import org.jclouds.compute.domain.NodeMetadata;
import org.jclouds.compute.domain.OperatingSystem;
import org.jclouds.compute.extensions.ImageExtension;
import org.jclouds.compute.extensions.SecurityGroupExtension;
import org.jclouds.compute.options.TemplateOptions;
import org.jclouds.domain.Location;
import org.jclouds.net.domain.IpPermission;
import org.jclouds.rest.AuthorizationException;
import org.jclouds.rest.suppliers.MemoizedRetryOnTimeOutButNotOnAuthorizationExceptionSupplier;

import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.base.Supplier;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.Provides;
import com.google.inject.TypeLiteral;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.name.Names;

public class AliyunComputeServiceContextModule extends
         ComputeServiceAdapterContextModule<VirtualMachine, ServiceOffering, Template, Zone> {

   @Override
   protected void configure() {
      super.configure();
      bind(new TypeLiteral<ComputeServiceAdapter<VirtualMachine, ServiceOffering, Template, Zone>>() {
      }).to(AliyunComputeServiceAdapter.class);
      bind(new TypeLiteral<Function<VirtualMachine, NodeMetadata>>() {
      }).to(VirtualMachineToNodeMetadata.class);
      bind(new TypeLiteral<Function<SecurityGroup, org.jclouds.compute.domain.SecurityGroup>>() {
      }).to(AliyunSecurityGroupToSecurityGroup.class);
      bind(new TypeLiteral<Function<IngressRule, IpPermission>>() {
      }).to(IngressRuleToIpPermission.class);
      bind(new TypeLiteral<Function<Template, org.jclouds.compute.domain.Image>>() {
      }).to(TemplateToImage.class);
      bind(new TypeLiteral<Function<ServiceOffering, org.jclouds.compute.domain.Hardware>>() {
      }).to(ServiceOfferingToHardware.class);
      bind(new TypeLiteral<Function<Zone, Location>>() {
      }).to(ZoneToLocation.class);
      bind(TemplateOptions.class).to(AliyunTemplateOptions.class);
      bind(new TypeLiteral<Function<Template, OperatingSystem>>() {
      }).to(TemplateToOperatingSystem.class);
      install(new FactoryModuleBuilder().build(StaticNATVirtualMachineInNetwork.Factory.class));
      bind(new TypeLiteral<CacheLoader<String, Set<IPForwardingRule>>>() {
      }).to(GetIPForwardingRulesByVirtualMachine.class);
      bind(new TypeLiteral<CacheLoader<String, Set<FirewallRule>>>() {
      }).to(GetFirewallRulesByVirtualMachine.class);
      bind(new TypeLiteral<CacheLoader<String, Zone>>() {
      }).to(ZoneIdToZone.class);
      bind(new TypeLiteral<CacheLoader<String, SshKeyPair>>() {
      }).to(CreateUniqueKeyPair.class);
      bind(new TypeLiteral<Supplier<LoadingCache<String, Zone>>>() {
      }).to(ZoneIdToZoneSupplier.class);
      bind(new TypeLiteral<Function<ZoneSecurityGroupNamePortsCidrs, SecurityGroup>>() {
      }).to(CreateSecurityGroupIfNeeded.class);
      bind(new TypeLiteral<CacheLoader<ZoneAndName, SecurityGroup>>() {
      }).to(FindSecurityGroupOrCreate.class);
      bind(new TypeLiteral<Function<Set<? extends NodeMetadata>,  Multimap<String, String>>>() {
      }).to(OrphanedGroupsByZoneId.class);

      bind(new TypeLiteral<ImageExtension>() {
      }).to(AliyunImageExtension.class);

      bind(new TypeLiteral<SecurityGroupExtension>() {
      }).to(AliyunSecurityGroupExtension.class);

      // to have the compute service adapter override default locations
      install(new LocationsFromComputeServiceAdapterModule<VirtualMachine, ServiceOffering, Template, Zone>() {
      });
   }
   

   @Override
   protected TemplateOptions provideTemplateOptions(Injector injector, TemplateOptions options) {
      return options.as(AliyunTemplateOptions.class)
         .generateKeyPair(injector.getInstance(
                  Key.get(boolean.class, Names.named(AUTO_GENERATE_KEYPAIRS))));
   }

   @Provides
   @Singleton
   @Memoized
   public Supplier<Map<String, String>> listOSCategories(AtomicReference<AuthorizationException> authException, @Named(PROPERTY_SESSION_INTERVAL) long seconds,
         final AliyunApi client) {
      return MemoizedRetryOnTimeOutButNotOnAuthorizationExceptionSupplier.create(authException,
            new Supplier<Map<String, String>>() {
               @Override
               public Map<String, String> get() {
                  GuestOSApi guestOSClient = client.getGuestOSApi();
                  return guestOSClient.listOSCategories();
               }
               @Override
               public String toString() {
                  return Objects.toStringHelper(client.getGuestOSApi()).add("method", "listOSCategories").toString();
               }
            }, seconds, TimeUnit.SECONDS);
   }

   @Provides
   @Singleton
   @Memoized
   public Supplier<Map<String, OSType>> listOSTypes(AtomicReference<AuthorizationException> authException, @Named(PROPERTY_SESSION_INTERVAL) long seconds,
         final AliyunApi client) {
      return MemoizedRetryOnTimeOutButNotOnAuthorizationExceptionSupplier.create(authException,
            new Supplier<Map<String, OSType>>() {
               @Override
               public Map<String, OSType> get() {
                  GuestOSApi guestOSClient = client.getGuestOSApi();
                  return Maps.uniqueIndex(guestOSClient.listOSTypes(), new Function<OSType, String>() {

                     @Override
                     public String apply(OSType arg0) {
                        return arg0.getId();
                     }
                  });
               }
               @Override
               public String toString() {
                  return Objects.toStringHelper(client.getGuestOSApi()).add("method", "listOSTypes").toString();
               }
            }, seconds, TimeUnit.SECONDS);
   }

   @Provides
   @Singleton
   @Memoized
   public Supplier<Map<String, Network>> listNetworks(AtomicReference<AuthorizationException> authException, @Named(PROPERTY_SESSION_INTERVAL) long seconds,
         final NetworksForCurrentUser networksForCurrentUser) {
      return MemoizedRetryOnTimeOutButNotOnAuthorizationExceptionSupplier.create(authException, networksForCurrentUser,
               seconds, TimeUnit.SECONDS);
   }

   @Provides
   @Singleton
   @Memoized
   public Supplier<Map<String, Project>> listProjects(AtomicReference<AuthorizationException> authException, @Named(PROPERTY_SESSION_INTERVAL) long seconds,
                                                      final ProjectsForCurrentUser projectsForCurrentUser) {
      return MemoizedRetryOnTimeOutButNotOnAuthorizationExceptionSupplier.create(authException, projectsForCurrentUser,
              seconds, TimeUnit.SECONDS);
   }

   @Provides
   @Singleton
   @Memoized
   public Supplier<User> getCurrentUser(AtomicReference<AuthorizationException> authException, @Named(PROPERTY_SESSION_INTERVAL) long seconds,
         final GetCurrentUser getCurrentUser) {
      return MemoizedRetryOnTimeOutButNotOnAuthorizationExceptionSupplier.create(authException, getCurrentUser,
               seconds, TimeUnit.SECONDS);
   }

   @Provides
   @Singleton
   protected Predicate<String> jobComplete(JobComplete jobComplete) {
      return retry(jobComplete, 1200, 1, 5, SECONDS);
   }

   @Provides
   @Singleton
   protected LoadingCache<String, SshKeyPair> keyPairMap(
         CacheLoader<String, SshKeyPair> in) {
      return CacheBuilder.newBuilder().build(in);
   }

   @Provides
   @Singleton
   protected LoadingCache<ZoneAndName, SecurityGroup> securityGroupMap(
            CacheLoader<ZoneAndName, SecurityGroup> in) {
      return CacheBuilder.newBuilder().build(in);
   }

   @Provides
   @Singleton
   protected LoadingCache<String, Set<IPForwardingRule>> getIPForwardingRulesByVirtualMachine(
      CacheLoader<String, Set<IPForwardingRule>> in) {
      return CacheBuilder.newBuilder().build(in);
   }


   @Provides
   @Singleton
   protected LoadingCache<String, Set<FirewallRule>> getFirewallRulesByVirtualMachine(
      CacheLoader<String, Set<FirewallRule>> getFirewallRules) {
      return CacheBuilder.newBuilder().build(getFirewallRules);
   }

   @Provides
   @Singleton
   public Map<NetworkType, ? extends OptionsConverter> optionsConverters() {
      return ImmutableMap.of(
         NetworkType.ADVANCED, new AdvancedNetworkOptionsConverter(),
         NetworkType.BASIC, new BasicNetworkOptionsConverter());
   }

   @Override
   protected Optional<ImageExtension> provideImageExtension(Injector i) {
      return Optional.of(i.getInstance(ImageExtension.class));
   }

   @Override
   protected Optional<SecurityGroupExtension> provideSecurityGroupExtension(Injector i) {
      return Optional.of(i.getInstance(SecurityGroupExtension.class));
   }
}
