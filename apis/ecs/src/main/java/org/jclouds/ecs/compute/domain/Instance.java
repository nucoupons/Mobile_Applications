package org.jclouds.ecs.compute.domain;

import java.beans.ConstructorProperties;

import org.testng.xml.dom.Tag;

import com.google.common.base.Objects;
import com.google.common.base.Objects.ToStringHelper;
import com.sun.istack.internal.Nullable;

public class Instance {

	public static Builder<?> builder() {
		return new ConcreteBuilder();
	}

	public Builder<?> toBuilder() {
		return new ConcreteBuilder().fromInstance(this);
	}

	public abstract static class Builder<T extends Builder<T>> {
		protected abstract T self();

		protected String id;
		protected String account;

		/**
		 * @see Tag#getId()
		 */
		public T id(String id) {
			this.id = id;
			return self();
		}

		/**
		 * @see Tag#getAccount()
		 */
		public T account(String account) {
			this.account = account;
			return self();
		}

		public Instance build() {
			return new Instance(id, account);
		}

		public T fromInstance(Instance in) {
			return this.id(in.getId()).account(in.getAccount());
		}
	}

	private static class ConcreteBuilder extends Builder<ConcreteBuilder> {
		@Override
		protected ConcreteBuilder self() {
			return this;
		}
	}

	private final String id;
	private final String account;

	@ConstructorProperties({ "id", "account" })
	protected Instance(@Nullable String id, @Nullable String account) {
		this.id = id;
		this.account = account;

	}

	@Nullable
	public String getId() {
		return this.id;
	}

	@Nullable
	public String getAccount() {
		return this.account;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id, account);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Instance that = Instance.class.cast(obj);
		return Objects.equal(this.id, that.id)
				&& Objects.equal(this.account, that.account);
	}

	protected ToStringHelper string() {
		return Objects.toStringHelper(this).add("id", id)
				.add("account", account);
	}

	@Override
	public String toString() {
		return string().toString();
	}

}
