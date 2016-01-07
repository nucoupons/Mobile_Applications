package org.jclouds.test.aliyun.ecs;

import org.jclouds.ContextBuilder;
import org.jclouds.compute.ComputeService;
import org.jclouds.compute.ComputeServiceContext;
import org.jclouds.compute.domain.ComputeMetadata;
import org.jclouds.logging.slf4j.config.SLF4JLoggingModule;
import org.jclouds.sshj.config.SshjSshClientModule;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableSet;
import com.google.inject.Module;

@Test(groups = "unit", testName = "Client")
public class ClientTest {

	public void test1() {

		ComputeServiceContext context = ContextBuilder
				.newBuilder("aliyun-ecs")
				.credentials("bnF9nNdDFCTwM5mF",
						"Z1mdYKAUt4q2OzyDhSd5qcnMUamQdD")
				.modules(
						ImmutableSet.<Module> of(new SLF4JLoggingModule(),
								new SshjSshClientModule()))
				.buildView(ComputeServiceContext.class);

		ComputeService client = context.getComputeService();
		client.listAssignableLocations();
		client.listImages();
		for (ComputeMetadata node : client.listNodes()) {
			System.out.println("node"+node.getId());
			node.getId(); // how does jclouds address this in a global scope
			node.getProviderId(); // how does the provider api address this in a
									// specific scope
			node.getName(); // if the node is named, what is it?
			node.getLocation(); // where in the world is the node
		}

	}

}
