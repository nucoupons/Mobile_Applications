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
package org.jclouds.hpcloud.objectstorage.blobstore.integration;

import org.jclouds.blobstore.domain.Blob;
import org.jclouds.openstack.swift.blobstore.integration.SwiftBlobIntegrationLiveTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test(groups = "live")
public class HPCloudObjectStorageBlobIntegrationLiveTest extends SwiftBlobIntegrationLiveTest {
   public HPCloudObjectStorageBlobIntegrationLiveTest() {
      provider = "hpcloud-objectstorage";
   }

    /**
     * HP Cloud Object Storage container naming rules have more restrictions than defaults
     * @see <a href="http://docs.hpcloud.com/api/object-storage#naming" />
     */
   @DataProvider(name = "delete")
   public Object[][] createData() {
      if (System.getProperty("os.name").toLowerCase().contains("windows")) {
         return new Object[][] { { "normal" }, { "sp ace" } };
      } else {
         return new Object[][] { { "normal" }, { "sp ace" }, { "qu?stion" }, { "unic₪de" }, { "path/foo" }, { "colon:" },
               { "asteri*k" }, { "p|pe" } };
      }
   }

   @Override
   protected void checkContentDisposition(Blob blob, String contentDisposition) {
      assert blob.getPayload().getContentMetadata().getContentDisposition().startsWith(contentDisposition) : blob
               .getPayload().getContentMetadata().getContentDisposition();
      assert blob.getMetadata().getContentMetadata().getContentDisposition().startsWith(contentDisposition) : blob
               .getMetadata().getContentMetadata().getContentDisposition();
   }

}
