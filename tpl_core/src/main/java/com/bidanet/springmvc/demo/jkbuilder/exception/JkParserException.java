package com.bidanet.springmvc.demo.jkbuilder.exception;

public class JkParserException extends RuntimeException {
    public JkParserException(String message) {
        super(message);
    }

    public JkParserException(String message, Throwable cause) {
        super(message, cause);
    }
}
