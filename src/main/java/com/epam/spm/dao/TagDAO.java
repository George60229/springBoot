package com.epam.spm.dao;

import com.epam.spm.dto.CreateTagDTO;
import com.epam.spm.dto.TagDTO;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


public interface TagDAO extends EntityDAO<TagDTO> {

    CreateTagDTO create(CreateTagDTO tag);



}
