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
package org.jclouds.ecs.config;




import org.jclouds.aws.config.FormSigningHttpApiModule;
import org.jclouds.ecs.EcsApi;
import org.jclouds.location.config.LocationModule;
import org.jclouds.location.suppliers.RegionIdsSupplier;
import org.jclouds.location.suppliers.ZoneIdToURISupplier;
import org.jclouds.location.suppliers.ZoneIdsSupplier;
import org.jclouds.location.suppliers.derived.RegionIdsFromRegionIdToURIKeySet;
import org.jclouds.location.suppliers.derived.ZoneIdToURIFromJoinOnRegionIdToURI;
import org.jclouds.location.suppliers.derived.ZoneIdsFromRegionIdToZoneIdsValues;
import org.jclouds.rest.ConfiguresHttpApi;

import com.google.inject.Scopes;

/**
 * Configures the EC2 connection.
 */
@ConfiguresHttpApi
public abstract class BaseEcsHttpApiModule<A extends EcsApi> extends
         FormSigningHttpApiModule<A> {

   protected BaseEcsHttpApiModule(Class<A> api) {
      super(api);
   }

   @Override
   protected void installLocations() {
      install(new LocationModule());
      bind(ZoneIdsSupplier.class).to(ZoneIdsFromRegionIdToZoneIdsValues.class).in(Scopes.SINGLETON);
      bind(RegionIdsSupplier.class).to(RegionIdsFromRegionIdToURIKeySet.class).in(Scopes.SINGLETON);
      bind(ZoneIdToURISupplier.class).to(ZoneIdToURIFromJoinOnRegionIdToURI.class).in(Scopes.SINGLETON);
   }
}
