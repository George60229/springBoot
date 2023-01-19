package com.epam.spm.dao.enumeration;

public enum CertificateParameters {
    DESCRIPTION("description"),
    DURATION("duration"),
    PRICE("price"),
    NAME("name"),
    CREATE_DATE("create_date");

    CertificateParameters(String message) {
        this.message = message;
    }

    private final String message;

    public String getMessage() {
        return message;
    }
}
