package com.epam.spm.exception;

public enum ErrorCode {
    CERTIFICATE_NOT_FOUND(40401, "Certificate is not found"), TAG_NOT_FOUND(40402, "Tag is not found");
    //todo fix it to one exception

    private final int code;
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
