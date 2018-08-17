
package com.shashank.phhub.core.dto;

import java.io.Serializable;

import com.shashank.phhub.base.common.enums.Errors;
import com.shashank.phhub.base.common.enums.Results;
import com.shashank.phhub.base.common.utils.GsonProvider;

public class GenericResponse implements Serializable {

	private static final long serialVersionUID = 203024160428521170L;

	private int success;
	private String result;
	private String message;
	private String error;
	private String description;
	private Object data;

	private GenericResponse(Builder builder) {
		this.success = builder.success;
		this.result = builder.result;
		this.message = builder.message;
		this.error = builder.error;
		this.description = builder.description;
		this.data = builder.data;
	}

	public int getSuccess() {
		return success;
	}

	public String getResult() {
		return result;
	}

	public String getMessage() {
		return message;
	}

	public String getError() {
		return error;
	}

	public String getDescription() {
		return description;
	}

	public Object getData() {
		return data;
	}

	public static class Builder {

		private int success;
		private String result;
		private String message;
		private String error;
		private String description;
		private Object data;

		public Builder() {
		}

		public Builder result(Results result) {
			this.success = 1;
			this.result = result.name();
			this.description = result.getDescription();
			return this;
		}

		public Builder description(String description) {
			this.description = description;
			return this;
		}

		public Builder success() {
			this.success = 1;
			return this;
		}

		public Builder message(String message) {
			this.message = message;
			return this;
		}

		public Builder error(Errors error) {
			this.success = 0;
			this.error = error.name();
			this.description = error.getDescription();
			return this;
		}

		public Builder data(Object data) {
			this.data = data;
			return this;
		}

		public GenericResponse build() {
			return new GenericResponse(this);
		}
	}

	@Override
	public String toString() {
		return GsonProvider.gsonDisplayAll.toJson(this);
	}

}
