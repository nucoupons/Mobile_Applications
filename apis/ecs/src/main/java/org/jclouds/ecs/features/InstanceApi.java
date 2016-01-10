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
import javax.ws.rs.GET;

import org.jclouds.Fallbacks.EmptySetOnNotFoundOr404;
import org.jclouds.aws.filters.FormSigner;
import org.jclouds.rest.annotations.Fallback;
import org.jclouds.rest.annotations.QueryParams;
import org.jclouds.rest.annotations.RequestFilters;
import org.jclouds.rest.annotations.SelectJson;
import org.jclouds.rest.annotations.SinceApiVersion;
import org.jclouds.rest.annotations.VirtualHost;

import com.sun.jna.platform.win32.Advapi32Util.Account;

/**
 * Provides access to EC2 Instance Services via their REST API.
 * <p/>
 */
@RequestFilters(FormSigner.class)
@VirtualHost
public interface InstanceApi {

	/**
	 * Lists Accounts
	 * 
	 * @param options
	 *            if present, how to constrain the list.
	 * @return Accounts matching query, or empty set, if no Accounts are found
	 */
	@SinceApiVersion("2014-05-24")
	@Named("test")
	@GET
	@QueryParams(keys = { "Action", "listAll" }, values = { "test","true" })
	@SelectJson("account")
	@Fallback(EmptySetOnNotFoundOr404.class)
	Set<Account> test();

}
