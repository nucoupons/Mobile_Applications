package org.jclouds.aliyun.test;

import static org.testng.Assert.assertNotNull;

import org.jclouds.aliyun.AliyunContext;
import org.jclouds.aliyun.features.AccountApi;
import org.jclouds.aliyun.internal.test.BaseAliyunExpectTest;
import org.jclouds.http.HttpRequest;
import org.jclouds.http.HttpResponse;
import org.testng.annotations.Test;

@Test(groups = "unit", testName = "CommonApiExpectTest")
public class CommonApiTest extends BaseAliyunExpectTest<AccountApi> {

	protected final HttpRequest login = HttpRequest.builder().method("GET")
			.endpoint("https://ecs.aliyuncs.com/")
			.addQueryParam("Format", "json")
			.addQueryParam("Version", "2014-05-26")
			.addQueryParam("AccessKeyId", "Z1mdYKAUt4q2OzyDhSd5qcnMUamQdD")
			.addQueryParam("Timestamp", "2012-06-01T12:00:00Z")

			.build();

	protected final HttpResponse loginResponse = HttpResponse.builder()
			.statusCode(200).build();

	@Override
	protected AccountApi clientFrom(AliyunContext context) {
		return context.getApi().getAccountApi();
	}

	public void testCommon() {

		AccountApi client = requestSendsResponse(login, loginResponse);
		assertNotNull(client.listAccounts());
	}

}
