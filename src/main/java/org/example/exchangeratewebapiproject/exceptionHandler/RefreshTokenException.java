package org.example.exchangeratewebapiproject.exceptionHandler;

public class RefreshTokenException extends  RuntimeException{

    private String message;

    public RefreshTokenException() {}

    public RefreshTokenException(String msg) {
        super(msg);
        this.message = msg;
    }
}
