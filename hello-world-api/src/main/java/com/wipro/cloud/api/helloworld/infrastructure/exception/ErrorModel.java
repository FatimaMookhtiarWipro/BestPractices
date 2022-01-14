package com.wipro.cloud.api.helloworld.infrastructure.exception;

import java.util.List;

public class ErrorModel {

	private int httpCode;
	private String httpMessage;
	private List<ErrorDetail> errors;


	private ErrorModel(ErrorModelBuilder ebuilder) {
		this.httpCode = ebuilder.ebHttpCode;
		this.httpMessage = ebuilder.ebHttpMessage;
		this.errors = ebuilder.ebErrors;
	}

	public int getHttpCode() {
		return httpCode;
	}

	public String getHttpMessage() {
		return httpMessage;
	}

	public List<ErrorDetail> getErrors() {
		return errors;
	}


	public static class ErrorModelBuilder {
		private int ebHttpCode;
		private String ebHttpMessage;
		private List<ErrorDetail> ebErrors;

		public ErrorModelBuilder withHttpCode(int httpCode) {
			ebHttpCode = httpCode;
			return this;
		}

		public ErrorModelBuilder withErrors(List<ErrorDetail> errors) {
			ebErrors = errors;
			return this;
		}

		public ErrorModelBuilder withHttpMessage(String httpMessage) {
			ebHttpMessage = httpMessage;
			return this;
		}


		public ErrorModel build() {
			return new ErrorModel(this);
		}

	}
	
}