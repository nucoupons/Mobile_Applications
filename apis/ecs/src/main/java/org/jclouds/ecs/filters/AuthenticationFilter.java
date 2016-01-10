package org.jclouds.ecs.filters;

import org.jclouds.http.HttpRequestFilter;

import com.google.inject.ImplementedBy;


@ImplementedBy(QuerySigner.class)
public interface AuthenticationFilter extends HttpRequestFilter{

}
