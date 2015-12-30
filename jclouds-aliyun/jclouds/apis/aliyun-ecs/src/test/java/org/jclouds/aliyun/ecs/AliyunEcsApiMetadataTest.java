package org.jclouds.aliyun.ecs;

import org.jclouds.compute.internal.BaseComputeServiceApiMetadataTest;
import org.testng.annotations.Test;

@Test(groups = "unit", testName = "AliyunEcsApiMetadataTest")
public class AliyunEcsApiMetadataTest extends BaseComputeServiceApiMetadataTest {

	public AliyunEcsApiMetadataTest() {
       super(new AliyunEcsApiMetadata());
	}


}