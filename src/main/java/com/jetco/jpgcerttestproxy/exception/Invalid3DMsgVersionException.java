package com.jetco.jpgcerttestproxy.exception;

public class Invalid3DMsgVersionException extends Exception {

	public Invalid3DMsgVersionException() {}

    // Constructor that accepts a message
    public Invalid3DMsgVersionException(String message)
    {
       super(message);
    }
}
