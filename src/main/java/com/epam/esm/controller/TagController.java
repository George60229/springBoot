package com.epam.esm.controller;


import com.epam.esm.dto.request.TagRequestDTO;
import com.epam.esm.dto.response.TagResponseDTO;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/getTagById{id}")
    public TagResponseDTO getTagByID(@PathVariable(value = "id") int id) {
        return tagService.getById(id);
    }

    @GetMapping("/getTagByName{name}")
    public List<TagResponseDTO> getTagByName(@PathVariable(value = "name") String name) {
        return tagService.getByName(name);
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
