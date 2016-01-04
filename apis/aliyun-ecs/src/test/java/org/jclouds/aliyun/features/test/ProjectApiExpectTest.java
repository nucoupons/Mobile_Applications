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
package org.jclouds.aliyun.features.test;

import static org.testng.Assert.assertEquals;

import java.util.Set;

import org.jclouds.aliyun.AliyunContext;
import org.jclouds.aliyun.domain.Project;
import org.jclouds.aliyun.domain.Tag;
import org.jclouds.aliyun.features.ProjectApi;
import org.jclouds.aliyun.internal.test.BaseAliyunExpectTest;
import org.jclouds.http.HttpRequest;
import org.jclouds.http.HttpResponse;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableSet;

/**
 * Test the Aliyun ProjectApi
 */
@Test(groups = "unit", testName = "ProjectApiExpectTest")
public class ProjectApiExpectTest extends BaseAliyunExpectTest<ProjectApi> {


   public void testListProjectsWhenResponseIs2xx() {

      ProjectApi client = requestSendsResponse(
         HttpRequest.builder()
            .method("GET")
            .endpoint("https://ecs.aliyuncs.com/?response=json&command=listProjects&listAll=true&apiKey=identity&signature=vtCqaYXfXttr6mD18Es0e22QBIQ%3D")
            .addHeader("Accept", "application/json")
            .build(),
         HttpResponse.builder()
            .statusCode(200)
            .payload(payloadFromResource("/listprojectsresponse.json"))
            .build());

      Set<Project> projects = ImmutableSet.of(
            Project.builder()
                  .id("489da162-0b77-489d-b044-ce39aa018b1f")
                  .account("thyde")
                  .displayText("")
                  .domain("ROOT")
                  .domainId("41a4917b-7952-499d-ba7f-4c57464d3dc8")
                  .name("NN-HA-T1")
                  .state(Project.State.ACTIVE).build(),
            Project.builder()
                  .id("1c11f22c-15ac-4fa7-b833-4d748df317b7")
                  .account("prasadm")
                  .displayText("Hive")
                  .domain("ROOT")
                  .domainId("41a4917b-7952-499d-ba7f-4c57464d3dc8")
                  .name("hive")
                  .state(Project.State.ACTIVE)
                  .tags(Tag.builder()
                        .account("prasadm")
                        .domain("ROOT")
                        .domainId("41a4917b-7952-499d-ba7f-4c57464d3dc8")
                        .key("some-tag")
                        .resourceId("1c11f22c-15ac-4fa7-b833-4d748df317b7")
                        .resourceType(Tag.ResourceType.PROJECT)
                        .value("some-value")
                        .build())
                  .build());

      assertEquals(client.listProjects(), projects);
   }

   @Override
   protected ProjectApi clientFrom(AliyunContext context) {
      return context.getApi().getProjectApi();
   }
}
