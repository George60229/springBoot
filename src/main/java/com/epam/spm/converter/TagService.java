package com.epam.spm.converter;

import com.epam.spm.dto.TagDTO;
import com.epam.spm.model.Tag;

import java.util.List;

public interface TagService {
    List<TagDTO> convert(List<Tag> tags);
}
