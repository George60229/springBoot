package com.epam.spm.exception;

public class CertificateNotFoundException extends IllegalArgumentException {
    public CertificateNotFoundException(String s) {
        super(s);
    }
}
