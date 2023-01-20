package com.epam.esm.service;

import com.epam.esm.converter.TagConverter;
import com.epam.esm.dao.TagDAO;
import com.epam.esm.dto.request.TagRequestDTO;
import com.epam.esm.dto.response.TagResponseDTO;
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
