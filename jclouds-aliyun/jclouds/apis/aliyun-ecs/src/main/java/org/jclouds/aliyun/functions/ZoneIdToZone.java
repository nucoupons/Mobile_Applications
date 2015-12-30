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
package org.jclouds.aliyun.functions;

import static com.google.common.base.Preconditions.checkNotNull;

import org.jclouds.aliyun.CloudStackApi;
import org.jclouds.aliyun.domain.Zone;
import org.jclouds.aliyun.features.ZoneApi;

import com.google.common.cache.CacheLoader;
import com.google.inject.Inject;

/**
 * Defines a cache that allows a zone to be looked up by its ID.
 */
public class ZoneIdToZone extends CacheLoader<String, Zone> {

   private final ZoneApi zoneClient;

   @Inject
   public ZoneIdToZone(CloudStackApi client) {
      checkNotNull(client, "client");
      this.zoneClient = client.getZoneApi();
   }

   @Override
   public Zone load(String zoneId) throws Exception {
      checkNotNull(zoneId, "zoneId");
      return zoneClient.getZone(zoneId);
   }

}
