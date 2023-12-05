package com.projectname.qa.exception;

public class FrameworkException extends RuntimeException {

	public FrameworkException() {
		super("Framework Exception...");
	}
	
	public FrameworkException(String mesg) {
		super(mesg);
	}

}
