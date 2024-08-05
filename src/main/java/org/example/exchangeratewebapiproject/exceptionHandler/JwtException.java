package org.example.exchangeratewebapiproject.exceptionHandler;

public class JwtException extends  RuntimeException{
    public JwtException() {}

    public JwtException(String msg) {
        super(msg);
    }
}
