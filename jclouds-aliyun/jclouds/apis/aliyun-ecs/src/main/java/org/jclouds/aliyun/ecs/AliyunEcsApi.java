package org.jclouds.aliyun.ecs;

import java.io.Closeable;

import org.jclouds.aliyun.ecs.features.SSHKeyPairApi;
import org.jclouds.aliyun.ecs.features.SecurityGroupApi;
import org.jclouds.aliyun.ecs.features.ZoneApi;

public interface AliyunEcsApi extends Closeable {

	@Delegate
	ZoneApi getZoneApi();

	@Delegate
	SecurityGroupApi getSecurityGroupApi();

	@Delegate
	SSHKeyPairApi getSSHKeyPairApi();

}
