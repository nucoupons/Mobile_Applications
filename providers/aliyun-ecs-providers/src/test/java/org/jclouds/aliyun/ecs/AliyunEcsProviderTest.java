package org.jclouds.aliyun.ecs;

import org.jclouds.providers.internal.BaseProviderMetadataTest;
import org.testng.annotations.Test;

@Test(groups = "unit", testName = "AliyunEcsContextBuilderTest")
public class AliyunEcsProviderTest extends BaseProviderMetadataTest {

	public AliyunEcsProviderTest() {
		super(new AliyunEcsProviderMetadata(), new AliyunEcsApiMetadata());
	}

}
