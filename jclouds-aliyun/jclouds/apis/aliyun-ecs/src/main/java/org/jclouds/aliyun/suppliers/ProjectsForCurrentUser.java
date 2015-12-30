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
package org.jclouds.aliyun.suppliers;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.jclouds.aliyun.options.ListProjectsOptions.Builder.accountInDomain;

import java.util.Map;

import javax.inject.Inject;

import org.jclouds.aliyun.AliyunApi;
import org.jclouds.aliyun.domain.Project;
import org.jclouds.aliyun.domain.User;
import org.jclouds.aliyun.features.ProjectApi;
import org.jclouds.collect.Memoized;

import com.google.common.base.Function;
import com.google.common.base.Supplier;
import com.google.common.collect.Maps;

public class ProjectsForCurrentUser implements Supplier<Map<String, Project>> {
   private final AliyunApi api;
   private final Supplier<User> currentUserSupplier;

   @Inject
   public ProjectsForCurrentUser(AliyunApi api, @Memoized Supplier<User> currentUserSupplier) {
      this.api = checkNotNull(api, "api");
      this.currentUserSupplier = checkNotNull(currentUserSupplier, "currentUserSupplier");
   }

   @Override
   public Map<String, Project> get() {
      User currentUser = currentUserSupplier.get();
      ProjectApi projectApi = api.getProjectApi();
      return Maps.uniqueIndex(
            projectApi.listProjects(accountInDomain(currentUser.getAccount(), currentUser.getDomainId())),
            new Function<Project, String>() {

               @Override
               public String apply(Project arg0) {
                  return arg0.getId();
               }
            });
   }
}
