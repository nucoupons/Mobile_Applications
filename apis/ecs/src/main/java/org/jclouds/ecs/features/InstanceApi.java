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
package org.jclouds.ecs.features;

import java.util.Set;

import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.core.MediaType;

import org.jclouds.Fallbacks.EmptySetOnNotFoundOr404;
import org.jclouds.ecs.compute.domain.Instance;
import org.jclouds.ecs.filters.AuthenticationFilter;
import org.jclouds.rest.annotations.Fallback;
import org.jclouds.rest.annotations.QueryParams;
import org.jclouds.rest.annotations.RequestFilters;
import org.jclouds.rest.annotations.SelectJson;

/**
 * Provides access to EC2 Instance Services via their REST API.
 * <p/>
 */
@RequestFilters(AuthenticationFilter.class)
@QueryParams(keys = { "Format", "Version" }, values = { "json", "2014-05-26" })
public interface InstanceApi {

	/**
	 * Lists listInstances
	 * 
	 * @param options
	 *            if present, how to constrain the list.
	 * @return Accounts matching query, or empty set, if no Accounts are found
	 */
	@Named("test")
	@GET
	@QueryParams(keys = { "Action", "RegionId" }, values = { "DescribeRegions",
			"cn-qingdao" })
	@Consumes(MediaType.APPLICATION_JSON)
	@SelectJson("instance")
	@Fallback(EmptySetOnNotFoundOr404.class)
	Set<Instance> listInstances();

}
