package com.epam.spm.dto;

public class TagDTO {
    private Integer tagId;
    //shift+f6;

    private String name;


    public Integer getId() {
        return tagId;
    }

    public void setId(Integer id) {
        this.tagId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
