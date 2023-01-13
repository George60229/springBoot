package com.epam.spm.dao;

import com.epam.spm.dto.RequestTagDTO;
import com.epam.spm.dto.ResponseTagDTO;


public interface TagDAO extends EntityDAO<ResponseTagDTO> {

    RequestTagDTO create(RequestTagDTO tag);



}
