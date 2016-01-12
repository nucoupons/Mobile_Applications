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
package org.jclouds.ecs.compute.functions;

import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.inject.Named;
import javax.inject.Singleton;

import org.jclouds.compute.domain.Image;
import org.jclouds.compute.reference.ComputeServiceConstants;
import org.jclouds.ecs.domain.ServerImage;
import org.jclouds.logging.Logger;

import com.google.common.base.Function;

@Singleton
public class ServerImageToImage implements Function<ServerImage, Image> {
	public static final Pattern GOGRID_OS_PATTERN = Pattern
			.compile("([a-zA-Z]*).*");
	public static final Pattern GOGRID_VERSION_PATTERN = Pattern
			.compile(".* ([0-9.]+) .*");

	@Resource
	@Named(ComputeServiceConstants.COMPUTE_LOGGER)
	protected Logger logger = Logger.NULL;

	@Override
	public Image apply(ServerImage input) {
		// TODO Auto-generated method stub
		return null;
	}

}
