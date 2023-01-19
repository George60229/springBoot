package com.epam.spm.service;

import com.epam.spm.converter.TagConverter;
import com.epam.spm.dao.TagDAO;
import com.epam.spm.dto.request.TagRequestDTO;
import com.epam.spm.dto.response.TagResponseDTO;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface TagService {
    void setAll(TagDAO tagDAO,TagConverter tagConverter);

    TagResponseDTO createTag(@RequestBody TagRequestDTO tagDTO);

    List<TagResponseDTO> getAllTags();

    boolean deleteById(int id);

    TagResponseDTO getById(int id);

    List<TagResponseDTO> getByName(String name);


}
