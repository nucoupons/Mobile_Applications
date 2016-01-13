package org.jclouds.aliyun.ecs.compute.strategy;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.jclouds.aliyun.ecs.AliyunEcsApi;
import org.jclouds.compute.domain.Hardware;
import org.jclouds.compute.reference.ComputeServiceConstants;
import org.jclouds.compute.reference.ComputeServiceConstants.Timeouts;
import org.jclouds.ecs.compute.strategy.EcsComputeServiceAdapter;
import org.jclouds.logging.Logger;

import com.google.common.base.Function;

@Singleton
public class AliyunEcsComputeServiceAdapter extends EcsComputeServiceAdapter {

	@Resource
	@Named(ComputeServiceConstants.COMPUTE_LOGGER)
	protected Logger logger = Logger.NULL;

	@Inject
	protected AliyunEcsComputeServiceAdapter(AliyunEcsApi api,
			Function<Hardware, String> sizeToRam, Timeouts timeouts) {
		super(api, sizeToRam, timeouts);

	}

}
