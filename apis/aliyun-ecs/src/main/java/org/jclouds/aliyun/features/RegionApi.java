package org.jclouds.aliyun.features;

import java.util.Set;

import javax.inject.Named;
import javax.ws.rs.GET;

import org.jclouds.Fallbacks.EmptySetOnNotFoundOr404;
import org.jclouds.aliyun.domain.Region;
import org.jclouds.aliyun.filters.AuthenticationFilter;
import org.jclouds.aliyun.options.ListAccountsOptions;
import org.jclouds.rest.annotations.Fallback;
import org.jclouds.rest.annotations.QueryParams;
import org.jclouds.rest.annotations.RequestFilters;
import org.jclouds.rest.annotations.SelectJson;

@RequestFilters(AuthenticationFilter.class)
@QueryParams(keys = { "Format", "Version" }, values = { "json", "2014-05-26" })
public interface RegionApi {

	@Named("DescribeRegions")
	@GET
	@QueryParams(keys = { "Action" }, values = {
			"DescribeRegions"})
	@SelectJson("region")
	@Fallback(EmptySetOnNotFoundOr404.class)
	Set<Region> listAccounts(ListAccountsOptions... options);

}
