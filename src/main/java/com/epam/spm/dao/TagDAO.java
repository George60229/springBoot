package com.epam.spm.dao;

import com.epam.spm.dto.RequestTagDTO;
import com.epam.spm.dto.ResponseTagDTO;
import com.epam.spm.model.Tag;


public interface TagDAO extends EntityDAO<Tag> {

    RequestTagDTO create(RequestTagDTO tag);



}
