package com.epam.spm.exception;

public class Response {

    private String message;
    private int code;

    public Response() {
    }

    public Response(String message,int code) {
        this.message = message;
        this.code=code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
