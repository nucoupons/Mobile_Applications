package org.jclouds.aliyun.ecs;

import org.jclouds.aliyun.ecs.internal.AliyunEcsServiceContextImpl;
import org.jclouds.compute.ComputeServiceContext;

import com.google.inject.ImplementedBy;

@ImplementedBy(AliyunEcsServiceContextImpl.class)
public interface AliyunEcsServiceContext extends ComputeServiceContext {
	
	

}
