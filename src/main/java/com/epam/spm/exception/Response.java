package com.epam.spm.exception;

public class Response {

    private String message;
    private ErrorCode code;

    public Response() {
    }

    public Response(String message,ErrorCode code) {
        this.message = message;
        this.code=code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ErrorCode getCode() {
        return code;
    }

    public void setCode(ErrorCode code) {
        this.code = code;
    }
}
