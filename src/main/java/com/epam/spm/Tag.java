package com.epam.spm;

import org.springframework.stereotype.Component;

@Component("Tag")
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
}
