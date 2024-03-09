package com.example.aop_controlleradvice.exception;

public class ResourceProcessingException extends RuntimeException{

    public ResourceProcessingException() {
        super();
    }

    public ResourceProcessingException(final String message) {
        super(message);
    }
}
