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
package org.jclouds.ecs;

import java.io.Closeable;

import com.google.common.base.Optional;

/**
 * Provides access to EC2 features, broken up by feature group. Use of the
 * {@link Optional} type allows you to check to see if the underlying
 * implementation supports a particular feature before attempting to use it.
 * This is useful in clones like OpenStack, CloudStack, or Eucalyptus, which
 * track the api, but are always behind Amazon's service. In the case of Amazon
 * ({@code aws-ec2}), you can expect all features to be present.
 * 
 * 
 * Example
 * 
 * <pre>
 * Optional&lt;? extends WindowsApi&gt; windowsOption = ec2Api.getWindowsApi();
 * checkState(windowsOption.isPresent(),
 * 		&quot;windows feature required, but not present&quot;);
 * </pre>
 */
public interface EcsApi extends Closeable {

}
