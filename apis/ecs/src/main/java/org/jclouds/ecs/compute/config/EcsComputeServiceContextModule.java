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
package org.jclouds.ecs.compute.config;

import org.jclouds.compute.ComputeServiceAdapter;
import org.jclouds.compute.ComputeServiceContext;
import org.jclouds.compute.config.ComputeServiceAdapterContextModule;
import org.jclouds.compute.domain.Hardware;
import org.jclouds.compute.domain.Image;
import org.jclouds.compute.domain.NodeMetadata;
import org.jclouds.compute.options.TemplateOptions;
import org.jclouds.domain.Location;
import org.jclouds.ecs.compute.EcsComputeService;
import org.jclouds.ecs.compute.functions.HardwareToHardware;
import org.jclouds.ecs.compute.functions.HardwareToString;
import org.jclouds.ecs.compute.functions.OptionToLocation;
import org.jclouds.ecs.compute.functions.ServerImageToImage;
import org.jclouds.ecs.compute.functions.ServerToNodeMetadata;
import org.jclouds.ecs.compute.options.EcsTemplateOptions;
import org.jclouds.ecs.compute.strategy.EcsComputeServiceAdapter;
import org.jclouds.ecs.domain.Option;
import org.jclouds.ecs.domain.Server;
import org.jclouds.ecs.domain.ServerImage;

import com.google.common.base.Function;
import com.google.inject.TypeLiteral;

/**
 * Configures the {@link ComputeServiceContext}; requires
 * {@link EcsComputeService} bound.
 */
public class EcsComputeServiceContextModule
		extends
		ComputeServiceAdapterContextModule<Server, Hardware, ServerImage, Option> {

	@Override
	protected void configure() {
		super.configure();

		bind(new TypeLiteral<ComputeServiceAdapter<Server, Hardware, ServerImage, Option>>() {
				}).to(EcsComputeServiceAdapter.class);

		bind(new TypeLiteral<Function<Server, NodeMetadata>>() {
		}).to(ServerToNodeMetadata.class);

		bind(new TypeLiteral<Function<ServerImage, Image>>() {
		}).to(ServerImageToImage.class);

		bind(new TypeLiteral<Function<Option, Location>>() {
		}).to(OptionToLocation.class);

		bind(new TypeLiteral<Function<Hardware, String>>() {
		}).to(HardwareToString.class);
		
		
		bind(new TypeLiteral<Function<Hardware, Hardware>>() {
		}).to(HardwareToHardware.class);


		bind(TemplateOptions.class).to(EcsTemplateOptions.class);

		
	}

}
