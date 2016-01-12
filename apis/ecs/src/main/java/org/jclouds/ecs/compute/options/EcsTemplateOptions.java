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
package org.jclouds.ecs.compute.options;

import java.util.Map;

import org.jclouds.compute.options.TemplateOptions;

/**
 * Contains options supported by the
 * {@link ComputeService#createNodesInGroup(String, int, TemplateOptions)} and
 * {@link ComputeService#createNodesInGroup(String, int, TemplateOptions)}
 * operations on the <em>gogrid</em> provider.
 * 
 * <h2>Usage</h2> The recommended way to instantiate a
 * {@link EcsTemplateOptions} object is to statically import
 * {@code EcsTemplateOptions.*} and invoke a static creation method followed
 * by an instance mutator (if needed):
 * <p>
 * 
 * <pre>
 * import static org.jclouds.compute.options.EcsTemplateOptions.Builder.*;
 * ComputeService client = // get connection
 * templateBuilder.options(inboundPorts(22, 80, 8080, 443));
 * Set&lt;? extends NodeMetadata&gt; set = client.createNodesInGroup(tag, 2, templateBuilder.build());
 * </pre>
 * 
 * TODO add GoGrid specific options
 */
public class EcsTemplateOptions extends TemplateOptions implements Cloneable {
   @Override
   public EcsTemplateOptions clone() {
	   EcsTemplateOptions options = new EcsTemplateOptions();
      copyTo(options);
      return options;
   }

   @Override
   public void copyTo(TemplateOptions to) {
      super.copyTo(to);
   }

   public static final EcsTemplateOptions NONE = new EcsTemplateOptions();

   public static class Builder {
      // methods that only facilitate returning the correct object type

      /**
       * @see TemplateOptions#inboundPorts(int...)
       */
      public static EcsTemplateOptions inboundPorts(int... ports) {
         EcsTemplateOptions options = new EcsTemplateOptions();
         return EcsTemplateOptions.class.cast(options.inboundPorts(ports));
      }

      /**
       * @see TemplateOptions#blockOnPort(int, int)
       */
      public static EcsTemplateOptions blockOnPort(int port, int seconds) {
         EcsTemplateOptions options = new EcsTemplateOptions();
         return EcsTemplateOptions.class.cast(options.blockOnPort(port, seconds));
      }

      /**
       * @see TemplateOptions#userMetadata(Map)
       */
      public static EcsTemplateOptions userMetadata(Map<String, String> userMetadata) {
         EcsTemplateOptions options = new EcsTemplateOptions();
         return EcsTemplateOptions.class.cast(options.userMetadata(userMetadata));
      }

      /**
       * @see TemplateOptions#userMetadata(String, String)
       */
      public static EcsTemplateOptions userMetadata(String key, String value) {
         EcsTemplateOptions options = new EcsTemplateOptions();
         return EcsTemplateOptions.class.cast(options.userMetadata(key, value));
      }

      /**
       * @see TemplateOptions#nodeNames(Iterable)
       */
      public static EcsTemplateOptions nodeNames(Iterable<String> nodeNames) {
         EcsTemplateOptions options = new EcsTemplateOptions();
         return EcsTemplateOptions.class.cast(options.nodeNames(nodeNames));
      }

      /**
       * @see TemplateOptions#networks(Iterable)
       */
      public static EcsTemplateOptions networks(Iterable<String> networks) {
         EcsTemplateOptions options = new EcsTemplateOptions();
         return EcsTemplateOptions.class.cast(options.networks(networks));
      }
   }

   // methods that only facilitate returning the correct object type

   /**
    * @see TemplateOptions#blockOnPort(int, int)
    */
   @Override
   public EcsTemplateOptions blockOnPort(int port, int seconds) {
      return EcsTemplateOptions.class.cast(super.blockOnPort(port, seconds));
   }

   /**
    * @see TemplateOptions#inboundPorts(int...)
    */
   @Override
   public EcsTemplateOptions inboundPorts(int... ports) {
      return EcsTemplateOptions.class.cast(super.inboundPorts(ports));
   }

   /**
    * @see TemplateOptions#authorizePublicKey(String)
    */
   @Override
   public EcsTemplateOptions authorizePublicKey(String publicKey) {
      return EcsTemplateOptions.class.cast(super.authorizePublicKey(publicKey));
   }

   /**
    * @see TemplateOptions#installPrivateKey(String)
    */
   @Override
   public EcsTemplateOptions installPrivateKey(String privateKey) {
      return EcsTemplateOptions.class.cast(super.installPrivateKey(privateKey));
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public EcsTemplateOptions userMetadata(Map<String, String> userMetadata) {
      return EcsTemplateOptions.class.cast(super.userMetadata(userMetadata));
   }
   
   /**
    * {@inheritDoc}
    */
   @Override
   public EcsTemplateOptions userMetadata(String key, String value) {
      return EcsTemplateOptions.class.cast(super.userMetadata(key, value));
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public EcsTemplateOptions nodeNames(Iterable<String> nodeNames) {
      return EcsTemplateOptions.class.cast(super.nodeNames(nodeNames));
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public EcsTemplateOptions networks(Iterable<String> networks) {
      return EcsTemplateOptions.class.cast(super.networks(networks));
   }
}
