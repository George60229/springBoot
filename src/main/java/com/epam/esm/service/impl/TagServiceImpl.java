package com.epam.esm.service.impl;

import com.epam.esm.converter.TagConverter;
import com.epam.esm.dao.TagDAO;
import com.epam.esm.dto.request.TagRequestDTO;
import com.epam.esm.dto.response.TagResponseDTO;
import com.epam.esm.exception.AppNotFoundException;
import com.epam.esm.exception.ErrorCode;
import com.epam.esm.model.Tag;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagServiceImpl implements TagService {


    @Autowired
    TagDAO tagDAO;

    @Autowired
    TagConverter converter;


    @Override
    public void setAll(TagDAO tagDAO, TagConverter tagConverter) {
        this.tagDAO = tagDAO;
        this.converter = tagConverter;
    }

    @Override
    public TagResponseDTO createTag(TagRequestDTO tagDTO) {
        return converter.convertOneToDTO(tagDAO.create(converter.convertDTOtoModel(tagDTO)));
    }

    @Override
    public List<TagResponseDTO> getAllTags() {
        return converter.convert(tagDAO.getAllTags());
    }

    @Override
    public boolean deleteById(int id) {
        return tagDAO.deleteById(id);
    }

    @Override
    public TagResponseDTO getById(int id) {
        Optional<Tag> myTag = tagDAO.getById(id);
        if (myTag.isEmpty()) {
            throw new AppNotFoundException("Tag with this id " + id + " is not found", ErrorCode.TAG_NOT_FOUND);
        }
        return converter.convertOneToDTO(myTag.get());
    }

    public List<TagResponseDTO> getByName(String name) {
        return converter.convert(tagDAO.getByName(name));
    }
}
