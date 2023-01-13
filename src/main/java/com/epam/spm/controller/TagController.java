package com.epam.spm.controller;


import com.epam.spm.dao.TagDAO;
import com.epam.spm.dto.RequestTagDTO;
import com.epam.spm.dto.ResponseTagDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TagController {



    @Autowired
    private TagDAO tagDAO;//todo tagDAO

    @GetMapping("/getTag")
    public List<ResponseTagDTO> getTag(@RequestParam String name) {


        return tagDAO.getEntityByName(name);
    }

    @PostMapping("/addTag")
    public RequestTagDTO addTag(@RequestBody RequestTagDTO tag) {

        return tagDAO.create(tag);
    }


    @GetMapping("/getAllTags")
    public List<ResponseTagDTO> getAllTags() {

        return tagDAO.listItems();
    }


}
