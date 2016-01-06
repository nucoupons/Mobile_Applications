package org.jclouds.aliyun.compute.strategy;

import org.jclouds.aliyun.domain.ServiceOffering;
import org.jclouds.aliyun.domain.Template;
import org.jclouds.aliyun.domain.VirtualMachine;
import org.jclouds.aliyun.domain.Zone;
import org.jclouds.compute.ComputeServiceAdapter;

public class AliyunEcsComputeServiceAdapter  implements
ComputeServiceAdapter<VirtualMachine, ServiceOffering, Template, Zone> {

	@Override
	public org.jclouds.compute.ComputeServiceAdapter.NodeAndInitialCredentials<VirtualMachine> createNodeWithGroupEncodedIntoName(
			String arg0, String arg1, org.jclouds.compute.domain.Template arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void destroyNode(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Template getImage(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VirtualMachine getNode(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<ServiceOffering> listHardwareProfiles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Template> listImages() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Zone> listLocations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<VirtualMachine> listNodes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<VirtualMachine> listNodesByIds(Iterable<String> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void rebootNode(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resumeNode(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void suspendNode(String arg0) {
		// TODO Auto-generated method stub
		
	}

}
