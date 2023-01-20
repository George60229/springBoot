package com.epam.esm.converter;

import com.epam.esm.dto.request.TagRequestDTO;
import com.epam.esm.dto.response.TagResponseDTO;
import com.epam.esm.model.Tag;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TagConverter {

    public List<TagResponseDTO> convert(List<Tag> tags) {
        return tags.stream()
                .map(this::convertOneToDTO)
                .collect(Collectors.toList());

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
