package com.epam.spm.exception;

public class CertificateNotFoundException extends IllegalArgumentException {
    public CertificateNotFoundException() {
    }

    public CertificateNotFoundException(String s) {
        super(s);
    }
}
