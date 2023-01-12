package com.epam.spm.converter;
import com.epam.spm.dto.TagDTO;
import com.epam.spm.model.Tag;

import java.util.ArrayList;
import java.util.List;

public class TagServiceImpl implements TagService{
    @Override
    public List<TagDTO> convert(List<Tag> tags) {
        List<TagDTO> tagList = new ArrayList<>();
        for (Tag tag : tags) {
            TagDTO tagDTO = new TagDTO();
            tagDTO.setName(tag.getName());
            tagDTO.setId(tag.getId());
            tagList.add(tagDTO);

        }

        return tagList;
    }
}
