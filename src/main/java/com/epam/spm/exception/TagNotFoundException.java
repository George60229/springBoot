package com.epam.spm.exception;

public class TagNotFoundException extends IllegalArgumentException{
    public TagNotFoundException(String s) {
        super(s);
    }
}
