package org.example.exchangeratewebapiproject.exceptionHandler;

public class JwtTokenInvalidException extends  RuntimeException {
    public JwtTokenInvalidException() {}

    public JwtTokenInvalidException(String msg) {
        super(msg);
    }
}
