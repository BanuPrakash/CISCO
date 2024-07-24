package com.cisco.orderapp.api;

public class ResourceNotFoundException extends  Exception{
    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
