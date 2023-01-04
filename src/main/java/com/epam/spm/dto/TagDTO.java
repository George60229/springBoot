package com.epam.spm.dto;

public class TagDTO {
    //required parameters
    private String name;

    public TagDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
