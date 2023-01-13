package com.epam.spm.converter.impl;
import com.epam.spm.converter.TagConverter;
import com.epam.spm.dto.ResponseTagDTO;
import com.epam.spm.model.Tag;

import java.util.ArrayList;
import java.util.List;

public class TagConverterImpl implements TagConverter {
    @Override
    public List<ResponseTagDTO> convert(List<Tag> tags) {
        List<ResponseTagDTO> tagList = new ArrayList<>();
        for (Tag tag : tags) {
            ResponseTagDTO responseTagDTO = new ResponseTagDTO();
            responseTagDTO.setName(tag.getName());
            responseTagDTO.setId(tag.getId());
            tagList.add(responseTagDTO);

        }

        return tagList;
    }
}
