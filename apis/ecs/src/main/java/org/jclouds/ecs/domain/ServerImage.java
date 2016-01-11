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
package org.jclouds.ecs.domain;

import static com.google.common.base.Preconditions.checkNotNull;

import java.beans.ConstructorProperties;

import org.jclouds.javax.annotation.Nullable;

import com.google.common.base.Objects;
import com.google.common.base.Objects.ToStringHelper;
import com.google.common.base.Strings;
import com.google.common.primitives.Longs;

/**
 * Class ServerImage
 * 
 */
public class ServerImage implements Comparable<ServerImage> {

	public static Builder<?> builder() {
		return new ConcreteBuilder();
	}

	public Builder<?> toBuilder() {
		return new ConcreteBuilder().fromServerImage(this);
	}

	public abstract static class Builder<T extends Builder<T>> {
		protected abstract T self();

		protected long id;
		protected String name;
		protected String friendlyName;
		protected String description;

		/**
		 * @see ServerImage#getId()
		 */
		public T id(long id) {
			this.id = id;
			return self();
		}

		/**
		 * @see ServerImage#getName()
		 */
		public T name(String name) {
			this.name = name;
			return self();
		}

		/**
		 * @see ServerImage#getFriendlyName()
		 */
		public T friendlyName(String friendlyName) {
			this.friendlyName = friendlyName;
			return self();
		}

		/**
		 * @see ServerImage#getDescription()
		 */
		public T description(String description) {
			this.description = description;
			return self();
		}

		public ServerImage build() {
			return new ServerImage(id, name, friendlyName, description);
		}

		public T fromServerImage(ServerImage in) {
			return this.id(in.getId()).name(in.getName())
					.friendlyName(in.getFriendlyName())
					.description(in.getDescription());
		}
	}

	private static class ConcreteBuilder extends Builder<ConcreteBuilder> {
		@Override
		protected ConcreteBuilder self() {
			return this;
		}
	}

	private final long id;
	private final String name;
	private final String friendlyName;
	private final String description;

	@ConstructorProperties({ "id", "name", "friendlyName", "description" })
	protected ServerImage(long id, String name, String friendlyName,
			@Nullable String description) {
		this.id = id;
		this.name = checkNotNull(name, "name");
		this.friendlyName = checkNotNull(friendlyName, "friendlyName");
		this.description = Strings.nullToEmpty(description);

	}

	public long getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public String getFriendlyName() {
		return this.friendlyName;
	}

	public String getDescription() {
		return this.description;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id, name, friendlyName, description);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		ServerImage that = ServerImage.class.cast(obj);
		return Objects.equal(this.id, that.id)
				&& Objects.equal(this.name, that.name)
				&& Objects.equal(this.friendlyName, that.friendlyName)
				&& Objects.equal(this.description, that.description);
	}

	protected ToStringHelper string() {
		return Objects.toStringHelper(this).add("id", id).add("name", name)
				.add("friendlyName", friendlyName)
				.add("description", description);
	}

	@Override
	public String toString() {
		return string().toString();
	}

	@Override
	public int compareTo(ServerImage that) {
		if (that == null)
			return 1;
		if (this == that)
			return 0;
		return Longs.compare(id, that.getId());
	}
}
