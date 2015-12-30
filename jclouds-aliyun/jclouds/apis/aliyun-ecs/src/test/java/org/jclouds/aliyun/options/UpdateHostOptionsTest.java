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
package org.jclouds.aliyun.options;

import static org.jclouds.aliyun.options.UpdateHostOptions.Builder.allocationState;
import static org.jclouds.aliyun.options.UpdateHostOptions.Builder.hostTags;
import static org.jclouds.aliyun.options.UpdateHostOptions.Builder.osCategoryId;
import static org.testng.Assert.assertEquals;

import org.jclouds.aliyun.domain.AllocationState;
import org.jclouds.aliyun.options.UpdateHostOptions;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

/**
 * Tests behavior of {@code UpdateHostOptions}
 */
@Test(groups = "unit")
public class UpdateHostOptionsTest {

   public void testAllocationState() {
      UpdateHostOptions options = new UpdateHostOptions().allocationState(AllocationState.ENABLED);
      assertEquals(ImmutableList.of("Enabled"), options.buildQueryParameters().get("allocationstate"));
   }

   public void testAllocationStateStatic() {
      UpdateHostOptions options = allocationState(AllocationState.ENABLED);
      assertEquals(ImmutableList.of("Enabled"), options.buildQueryParameters().get("allocationstate"));
   }

   public void testHostTags() {
      UpdateHostOptions options = new UpdateHostOptions().hostTags(ImmutableSet.<String>of("foo", "bar", "baz"));
      assertEquals(ImmutableList.of("foo,bar,baz"), options.buildQueryParameters().get("hosttags"));
   }

   public void testHostTagsStatic() {
      UpdateHostOptions options = hostTags(ImmutableSet.<String>of("foo", "bar", "baz"));
      assertEquals(ImmutableList.of("foo,bar,baz"), options.buildQueryParameters().get("hosttags"));
   }

   public void testOsCategoryId() {
      UpdateHostOptions options = new UpdateHostOptions().osCategoryId("42");
      assertEquals(ImmutableList.of("42"), options.buildQueryParameters().get("oscategoryid"));
   }

   public void testOsCategoryIdStatic() {
      UpdateHostOptions options = osCategoryId("42");
      assertEquals(ImmutableList.of("42"), options.buildQueryParameters().get("oscategoryid"));
   }

}
