package com.epam.spm.controller;


import com.epam.spm.dto.request.TagRequestDTO;
import com.epam.spm.dto.response.TagResponseDTO;
import com.epam.spm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TagController {


    @Autowired
    private TagService tagService;

    @GetMapping("/getTag")
    public List<TagResponseDTO> getTag(@RequestParam String name) {


        return tagService.getTagByName(name);
    }

    @GetMapping("/getTagById{id}")
    public TagResponseDTO getTagByID(@PathVariable(value = "id") int id) {


        return tagService.getById(id);
    }

    @PostMapping("/addTag")
    public TagResponseDTO addTag(@RequestBody TagRequestDTO tag) {

        return tagService.createTag(tag);
    }


    @GetMapping("/getAllTags")
    public List<TagResponseDTO> getAllTags() {

        return tagService.getAllTags();
    }

    @DeleteMapping("/deleteTag/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTagById(@PathVariable(value = "id") int id) {
        tagService.deleteById(id);
    }


}
