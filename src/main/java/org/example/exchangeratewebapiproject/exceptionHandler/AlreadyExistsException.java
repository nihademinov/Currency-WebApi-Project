package org.example.exchangeratewebapiproject.exceptionHandler;

public class AlreadyExistsException extends  RuntimeException {


    private String message;

    public AlreadyExistsException() {}

    public AlreadyExistsException(String msg) {
        super(msg);
        this.message = msg;
    }
}
