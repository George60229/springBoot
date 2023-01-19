package com.epam.spm.converter;

import com.epam.spm.dto.request.TagRequestDTO;
import com.epam.spm.dto.response.TagResponseDTO;
import com.epam.spm.model.Tag;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TagConverter {

    public List<TagResponseDTO> convert(List<Tag> tags) {
        List<TagResponseDTO> tagList = new ArrayList<>();
        for (Tag tag : tags) {
            TagResponseDTO tagResponseDTO = new TagResponseDTO();
            tagResponseDTO.setName(tag.getName());
            tagResponseDTO.setId(tag.getId());
            tagList.add(tagResponseDTO);
        }
        return tagList;
    }

    public TagResponseDTO convertOneToDTO(Tag tag) {
        TagResponseDTO tagResponseDTO = new TagResponseDTO();
        tagResponseDTO.setId(tag.getId());
        tagResponseDTO.setName(tag.getName());
        return tagResponseDTO;
    }

    public Tag convertDTOtoModel(TagRequestDTO tagDTO) {
        Tag tag = new Tag();
        tag.setName(tagDTO.getName());
        return tag;
    }
}
