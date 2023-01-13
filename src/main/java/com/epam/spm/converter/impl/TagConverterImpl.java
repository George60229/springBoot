package com.epam.spm.converter.impl;
import com.epam.spm.converter.TagConverter;
import com.epam.spm.dto.RequestTagDTO;
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

    @Override
    public ResponseTagDTO convertOneToDTO(Tag tag) {
        ResponseTagDTO responseTagDTO = new ResponseTagDTO();
        responseTagDTO.setId(tag.getId());
        responseTagDTO.setName(tag.getName());
        return responseTagDTO;
    }

    @Override
    public ResponseTagDTO convertRequestToResponse(RequestTagDTO tag) {
        ResponseTagDTO responseTagDTO=new ResponseTagDTO();
        responseTagDTO.setName(tag.getName());
        return responseTagDTO;
    }
}
