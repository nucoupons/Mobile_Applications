package org.jclouds.aliyun.ecs.internal;

import org.jclouds.Context;
import org.jclouds.aliyun.ecs.AliyunEcsServiceContext;
import org.jclouds.compute.ComputeService;
import org.jclouds.compute.Utils;
import org.jclouds.compute.internal.ComputeServiceContextImpl;
import org.jclouds.location.Provider;

import com.google.common.reflect.TypeToken;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class AliyunEcsServiceContextImpl extends ComputeServiceContextImpl
		implements AliyunEcsServiceContext {

	@Inject
	AliyunEcsServiceContextImpl(@Provider Context backend,
			@Provider TypeToken<? extends Context> backendType,
			ComputeService computeService, Utils utils) {
		super(backend, backendType, computeService, utils);

	}

}
