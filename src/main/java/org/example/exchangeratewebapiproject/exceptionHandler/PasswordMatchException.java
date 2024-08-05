package org.example.exchangeratewebapiproject.exceptionHandler;

public class PasswordMatchException extends  RuntimeException{
    public PasswordMatchException() {}
    public PasswordMatchException(String message) {
        super(message);
    }
}
