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
package org.jclouds.ecs.compute.strategy;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.jclouds.compute.ComputeService;
import org.jclouds.compute.ComputeServiceAdapter;
import org.jclouds.compute.domain.Hardware;
import org.jclouds.compute.domain.Template;
import org.jclouds.compute.reference.ComputeServiceConstants;
import org.jclouds.compute.reference.ComputeServiceConstants.Timeouts;
import org.jclouds.ecs.EcsApi;
import org.jclouds.ecs.domain.Option;
import org.jclouds.ecs.domain.Server;
import org.jclouds.ecs.domain.ServerImage;
import org.jclouds.logging.Logger;
import com.google.common.base.Function;

/**
 * defines the connection between the {@link org.jclouds.gogrid.GoGridApi}
 * implementation and the jclouds {@link ComputeService}
 */
@Singleton
public class EcsComputeServiceAdapter implements
		ComputeServiceAdapter<Server, Hardware, ServerImage, Option> {

	@Resource
	@Named(ComputeServiceConstants.COMPUTE_LOGGER)
	protected Logger logger = Logger.NULL;

	private final EcsApi api;

	@Inject
	protected EcsComputeServiceAdapter(EcsApi api,
			Function<Hardware, String> sizeToRam, Timeouts timeouts) {
		this.api = checkNotNull(api, "api");

	}

	@Override
	public org.jclouds.compute.ComputeServiceAdapter.NodeAndInitialCredentials<Server> createNodeWithGroupEncodedIntoName(
			String arg0, String arg1, Template arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void destroyNode(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public ServerImage getImage(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Server getNode(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Hardware> listHardwareProfiles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<ServerImage> listImages() {
		System.out.println("listImages");
		return api.getServerImageApi().get().listServerImages();
	}

	@Override
	public Iterable<Option> listLocations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Server> listNodes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Server> listNodesByIds(Iterable<String> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void rebootNode(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void resumeNode(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void suspendNode(String arg0) {
		// TODO Auto-generated method stub

	}

}
