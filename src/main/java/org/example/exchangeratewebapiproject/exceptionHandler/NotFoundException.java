package org.example.exchangeratewebapiproject.exceptionHandler;

public class NotFoundException extends  RuntimeException {
    private String message;

    private int code = 404;
    public NotFoundException() {}

    public NotFoundException(String msg) {
        super(msg);
        this.message = msg;
    }
}
