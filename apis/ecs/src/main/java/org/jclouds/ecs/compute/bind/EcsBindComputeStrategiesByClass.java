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
package org.jclouds.ecs.compute.bind;

import org.jclouds.compute.config.BindComputeStrategiesByClass;
import org.jclouds.compute.strategy.CreateNodeWithGroupEncodedIntoName;
import org.jclouds.compute.strategy.CreateNodesInGroupThenAddToSet;
import org.jclouds.compute.strategy.DestroyNodeStrategy;
import org.jclouds.compute.strategy.GetImageStrategy;
import org.jclouds.compute.strategy.GetNodeMetadataStrategy;
import org.jclouds.compute.strategy.ListNodesStrategy;
import org.jclouds.compute.strategy.RebootNodeStrategy;
import org.jclouds.compute.strategy.ResumeNodeStrategy;
import org.jclouds.compute.strategy.SuspendNodeStrategy;
import org.jclouds.ecs.compute.strategy.EcsCreateNodesInGroupThenAddToSet;
import org.jclouds.ecs.compute.strategy.EcsDestroyNodeStrategy;
import org.jclouds.ecs.compute.strategy.EcsGetImageStrategy;
import org.jclouds.ecs.compute.strategy.EcsGetNodeMetadataStrategy;
import org.jclouds.ecs.compute.strategy.EcsListNodesStrategy;
import org.jclouds.ecs.compute.strategy.EcsRebootNodeStrategy;
import org.jclouds.ecs.compute.strategy.EcsResumeNodeStrategy;
import org.jclouds.ecs.compute.strategy.EcsSuspendNodeStrategy;

public class EcsBindComputeStrategiesByClass extends BindComputeStrategiesByClass {
   @Override
   protected Class<? extends CreateNodesInGroupThenAddToSet> defineRunNodesAndAddToSetStrategy() {
      return EcsCreateNodesInGroupThenAddToSet.class;
   }

   /**
    * not needed, as {@link EcsCreateNodesInGroupThenAddToSet} is used and is already set-based.
    */
   @Override
   protected Class<? extends CreateNodeWithGroupEncodedIntoName> defineAddNodeWithTagStrategy() {
      return null;
   }

   /**
    * not needed, as {@link EcsCreateNodesInGroupThenAddToSet} is used and is already set-based.
    */
   @Override
   protected void bindAddNodeWithTagStrategy(Class<? extends CreateNodeWithGroupEncodedIntoName> clazz) {
   }

   @Override
   protected Class<? extends DestroyNodeStrategy> defineDestroyNodeStrategy() {
      return EcsDestroyNodeStrategy.class;
   }

   @Override
   protected Class<? extends GetNodeMetadataStrategy> defineGetNodeMetadataStrategy() {
      return EcsGetNodeMetadataStrategy.class;
   }

   @Override
   protected Class<? extends GetImageStrategy> defineGetImageStrategy() {
      return EcsGetImageStrategy.class;
   }

   @Override
   protected Class<? extends ListNodesStrategy> defineListNodesStrategy() {
      return EcsListNodesStrategy.class;
   }

   @Override
   protected Class<? extends RebootNodeStrategy> defineRebootNodeStrategy() {
      return EcsRebootNodeStrategy.class;
   }

   @Override
   protected Class<? extends ResumeNodeStrategy> defineStartNodeStrategy() {
      return EcsResumeNodeStrategy.class;
   }

   @Override
   protected Class<? extends SuspendNodeStrategy> defineStopNodeStrategy() {
      return EcsSuspendNodeStrategy.class;
   }

}
