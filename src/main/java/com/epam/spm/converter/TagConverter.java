package com.epam.spm.converter;

import com.epam.spm.dto.RequestTagDTO;
import com.epam.spm.dto.ResponseTagDTO;
import com.epam.spm.model.Tag;

import java.util.List;

public interface TagConverter {
    List<ResponseTagDTO> convert(List<Tag> tags);
    ResponseTagDTO convertOneToDTO(Tag tag);
    ResponseTagDTO convertRequestToResponse(RequestTagDTO tag);
}
