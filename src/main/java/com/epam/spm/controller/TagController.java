package com.epam.spm.controller;


import com.epam.spm.dto.CreateTagDTO;
import com.epam.spm.dto.TagDTO;
import com.epam.spm.dao.impl.TagDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TagController {



    @Autowired
    private TagDAOImpl tagDAO;//todo tagDAO

    @GetMapping("/getTag")
    public List<TagDTO> getTag(@RequestParam String name) {


        return tagDAO.getEntityByName(name);
    }

    @PostMapping("/addTag")
    public CreateTagDTO addTag(@RequestBody CreateTagDTO tag) {

        return tagDAO.create(tag);
    }


    @GetMapping("/getAllTags")
    public List<TagDTO> getAllTags() {

        return tagDAO.listItems();
    }


}
