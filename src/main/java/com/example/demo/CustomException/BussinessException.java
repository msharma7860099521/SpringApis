package com.example.demo.CustomException;

public class BussinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private int exceptionCode;
	private String exceptionDescription;

	public int getExceptionCode() {
		return exceptionCode;
	}

	public void setExceptionCode(int exceptionCode) {
		this.exceptionCode = exceptionCode;
	}

	public String getExceptionDescription() {
		return exceptionDescription;
	}

	public void setExceptionDescription(String exceptionDescription) {
		this.exceptionDescription = exceptionDescription;
	}

	public BussinessException(int exceptionCode, String exceptionDescription) {
		super();
		this.exceptionCode = exceptionCode;
		this.exceptionDescription = exceptionDescription;
	}
	
	public BussinessException() {
	}

}
