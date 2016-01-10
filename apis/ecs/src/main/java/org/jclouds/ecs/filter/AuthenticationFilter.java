package org.jclouds.ecs.filter;

import org.jclouds.http.HttpRequestFilter;

import com.google.inject.ImplementedBy;


@ImplementedBy(QuerySigner.class)
public interface AuthenticationFilter extends HttpRequestFilter{

}
