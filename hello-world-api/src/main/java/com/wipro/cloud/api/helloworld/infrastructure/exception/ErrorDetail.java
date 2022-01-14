package com.wipro.cloud.api.helloworld.infrastructure.exception;

public class ErrorDetail {
    private Integer code;
	private String reasonCode;
	private String message;

	public ErrorDetail() {
	}

	public ErrorDetail(Integer code, String reasonCode, String message) {
		this.code = code;
		this.reasonCode = reasonCode;
		this.message = message;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ErrorDetail [code=" + code + ", reasonCode=" + reasonCode + ", message=" + message + "]";
	}
}
