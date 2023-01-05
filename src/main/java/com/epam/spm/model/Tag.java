package com.epam.spm.model;

import org.springframework.stereotype.Component;


public class Tag {


    private Integer tag_id;

    private String name;



    public Integer getId() {
        return tag_id;
    }

    public void setId(Integer id) {
        this.tag_id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "tag_id=" + tag_id +
                ", name='" + name + '\'' +
                '}';
    }
}