package com.epam.spm.dao;

import com.epam.spm.dto.RequestTagDTO;
import com.epam.spm.model.Tag;


public interface TagDAO extends EntityDAO<Tag> {

   Tag create(RequestTagDTO tag);



}
