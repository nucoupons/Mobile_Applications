/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jclouds.aliyun.features;

import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.jclouds.Fallbacks.NullOnNotFoundOr404;
import org.jclouds.aliyun.domain.Account;
import org.jclouds.aliyun.filters.AuthenticationFilter;
import org.jclouds.aliyun.options.CreateAccountOptions;
import org.jclouds.aliyun.options.UpdateAccountOptions;
import org.jclouds.rest.annotations.Fallback;
import org.jclouds.rest.annotations.QueryParams;
import org.jclouds.rest.annotations.RequestFilters;
import org.jclouds.rest.annotations.SelectJson;

/**
 * Provides synchronous access to Aliyun Account features available to Global
 * Admin users.
 *
 * @see <a href=
 *      "http://download.cloud.com/releases/2.2.0/api_2.2.12/TOC_Global_Admin.html"
 *      />
 */
@RequestFilters(AuthenticationFilter.class)
@QueryParams(keys = "response", values = "json")
public interface GlobalAccountApi extends DomainAccountApi {

   /**
    * Create a new Cloudstack account
    *
    * @param userName unique username.
    * @param accountType type of account
    * @param email
    * @param firstName
    * @param lastName
    * @param hashedPassword
    *          Hashed password (Default is MD5). If you wish to use any other
    *          hashing algorithm, you would need to write a custom authentication adapter See Docs section.
    * @param options
    *          optional parameters
    * @return
    */
   @Named("createAccount")
   @GET
   @QueryParams(keys = "command", values = "createAccount")
   @SelectJson("account")
   @Consumes(MediaType.APPLICATION_JSON)
   @Fallback(NullOnNotFoundOr404.class)
   Account createAccount(@QueryParam("username") String userName,
      @QueryParam("accounttype") Account.Type accountType, @QueryParam("email") String email,
      @QueryParam("firstname") String firstName, @QueryParam("lastname") String lastName,
      @QueryParam("password") String hashedPassword, CreateAccountOptions... options);

   /**
    * Update an existing account
    *
    * @param accountName the current account name
    * @param domainId the ID of the domain were the account exists
    * @param newName new name for the account
    * @param options optional arguments
    * @return
    */
   @Named("updateAccount")
   @GET
   @QueryParams(keys = "command", values = "updateAccount")
   @SelectJson("account")
   @Consumes(MediaType.APPLICATION_JSON)
   @Fallback(NullOnNotFoundOr404.class)
   Account updateAccount(@QueryParam("account") String accountName,
      @QueryParam("domainid") String domainId, @QueryParam("newname") String newName, UpdateAccountOptions... options);
   
   /**
    * Delete an account with the specified ID
    *
    * @param accountId
    * @return
    */
   @Named("deleteAccount")
   @GET
   @QueryParams(keys = "command", values = "deleteAccount")
   @Consumes(MediaType.APPLICATION_JSON)
   @Fallback(NullOnNotFoundOr404.class)
   void deleteAccount(@QueryParam("id") String id);
}
