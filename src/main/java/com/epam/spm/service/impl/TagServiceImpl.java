package com.epam.spm.service.impl;

import com.epam.spm.converter.TagConverter;
import com.epam.spm.converter.impl.TagConverterImpl;
import com.epam.spm.dao.TagDAO;
import com.epam.spm.dto.RequestTagDTO;
import com.epam.spm.dto.ResponseTagDTO;
import com.epam.spm.model.GiftCertificate;
import com.epam.spm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TagServiceImpl implements TagService {
    @Autowired
    TagDAO tagDAO;

    TagConverter converter=new TagConverterImpl();


    @Override
    public List<ResponseTagDTO> getTagByName(String name) {
        return converter.convert(tagDAO.getEntityByName(name));
    }

    @Override
    public ResponseTagDTO createCertificate(RequestTagDTO tagDTO) {
        return converter.convertRequestToResponse(tagDAO.create(tagDTO));
    }

    @Override
    public List<ResponseTagDTO> getAllTags() {
        return converter.convert(tagDAO.listItems());
    }
}
