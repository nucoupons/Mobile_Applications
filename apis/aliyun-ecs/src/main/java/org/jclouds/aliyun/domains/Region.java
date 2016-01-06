package org.jclouds.aliyun.domains;

import static com.google.common.base.Preconditions.checkNotNull;

import java.beans.ConstructorProperties;

import com.google.common.base.Objects;
import com.google.common.base.Objects.ToStringHelper;

public class Region implements Comparable<Region> {

	public static Builder<?> builder() {
		return new ConcreteBuilder();
	}

	public Builder<?> toBuilder() {
		return new ConcreteBuilder().fromRegion(this);
	}

	public abstract static class Builder<T extends Builder<T>> {
		protected abstract T self();

		protected String id;

		/**
		 * @see Zone#getId()
		 */
		public T id(String id) {
			this.id = id;
			return self();
		}

		public Region build() {
			return new Region(id);
		}

		public T fromRegion(Region in) {
			return this.id(in.getId());
		}
	}

	private static class ConcreteBuilder extends Builder<ConcreteBuilder> {
		@Override
		protected ConcreteBuilder self() {
			return this;
		}
	}

	private final String id;

	@ConstructorProperties({ "id" })
	protected Region(String id) {
		this.id = checkNotNull(id, "id");

	}

	/**
	 * @return Zone id
	 */
	public String getId() {
		return this.id;
	}
	
	  @Override
	   public boolean equals(Object obj) {
	      if (this == obj) return true;
	      if (obj == null || getClass() != obj.getClass()) return false;
	      Region that = Region.class.cast(obj);
	      return Objects.equal(this.id, that.id);
	   }

	   protected ToStringHelper string() {
	      return Objects.toStringHelper(this)
	            .add("id", id);
	   }


	@Override
	public String toString() {
		return string().toString();
	}

	@Override
	public int compareTo(Region o) {
		return id.compareTo(o.getId());
	}

}
