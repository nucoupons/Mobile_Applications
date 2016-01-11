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
import com.google.common.primitives.Longs;

/**
 * Class Server
 * 
 */
public class Server implements Comparable<Server> {

	public static Builder<?> builder() {
		return new ConcreteBuilder();
	}

	public Builder<?> toBuilder() {
		return new ConcreteBuilder().fromServer(this);
	}

	public abstract static class Builder<T extends Builder<T>> {
		protected abstract T self();

		protected long id;
		protected boolean isSandbox;
		protected String name;
		protected String description;

		/**
		 * @see Server#getId()
		 */
		public T id(long id) {
			this.id = id;
			return self();
		}

		/**
		 * @see Server#isSandbox()
		 */
		public T isSandbox(boolean isSandbox) {
			this.isSandbox = isSandbox;
			return self();
		}

		/**
		 * @see Server#getName()
		 */
		public T name(String name) {
			this.name = name;
			return self();
		}

		/**
		 * @see Server#getDescription()
		 */
		public T description(String description) {
			this.description = description;
			return self();
		}

		

		public Server build() {
			return new Server(id, isSandbox, name, description);
		}

		public T fromServer(Server in) {
			return this.id(in.getId()).isSandbox(in.isSandbox())
					.name(in.getName()).description(in.getDescription());
		}
	}

	private static class ConcreteBuilder extends Builder<ConcreteBuilder> {
		@Override
		protected ConcreteBuilder self() {
			return this;
		}
	}

	private final long id;
	private final boolean isSandbox;
	private final String name;
	private final String description;
	

	@ConstructorProperties({ "id", "isSandbox", "name", "description" })
	protected Server(long id, boolean isSandbox, String name,
			@Nullable String description) {
		this.id = id;
		this.isSandbox = isSandbox;
		this.name = checkNotNull(name, "name");
		this.description = description;
	}

	public long getId() {
		return this.id;
	}

	public boolean isSandbox() {
		return this.isSandbox;
	}

	public String getName() {
		return this.name;
	}

	@Nullable
	public String getDescription() {
		return this.description;
	}


	@Override
	public int hashCode() {
		return Objects.hashCode(id, isSandbox, name, description);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Server that = Server.class.cast(obj);
		return Objects.equal(this.id, that.id)
				&& Objects.equal(this.isSandbox, that.isSandbox)
				&& Objects.equal(this.name, that.name);
	}

	protected ToStringHelper string() {
		return Objects.toStringHelper(this).add("id", id)
				.add("isSandbox", isSandbox).add("name", name)
				.add("description", description);
	}

	@Override
	public String toString() {
		return string().toString();
	}

	@Override
	public int compareTo(Server that) {
		return Longs.compare(id, that.getId());
	}
}
