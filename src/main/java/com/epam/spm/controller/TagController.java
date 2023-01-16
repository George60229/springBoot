package com.epam.spm.controller;


import com.epam.spm.dao.TagDAO;
import com.epam.spm.dto.RequestTagDTO;
import com.epam.spm.dto.ResponseTagDTO;
import com.epam.spm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TagController {



    @Autowired
    private TagService tagService;//todo tagDAO

    @GetMapping("/getTag")
    public List<ResponseTagDTO> getTag(@RequestParam String name) {


        return tagService.getTagByName(name);
    }

    @PostMapping("/addTag")
    public ResponseTagDTO addTag(@RequestBody RequestTagDTO tag) {

        return tagService.createTag(tag);
    }


    @GetMapping("/getAllTags")
    public List<ResponseTagDTO> getAllTags() {

        return tagService.getAllTags();
    }


}
