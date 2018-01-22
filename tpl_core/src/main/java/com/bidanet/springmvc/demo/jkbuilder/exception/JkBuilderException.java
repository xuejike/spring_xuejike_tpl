package com.bidanet.springmvc.demo.jkbuilder.exception;

public class JkBuilderException extends RuntimeException {
    public JkBuilderException(String message) {
        super(message);
    }

    public JkBuilderException(String message, Throwable cause) {
        super(message, cause);
    }
}
