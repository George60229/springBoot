package com.epam.spm.service.impl;

import com.epam.spm.converter.TagConverter;
import com.epam.spm.dao.TagDAO;
import com.epam.spm.dto.request.TagRequestDTO;
import com.epam.spm.dto.response.TagResponseDTO;
import com.epam.spm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    TagDAO tagDAO;

    @Autowired
    TagConverter converter;

    @Override
    public TagResponseDTO createTag(TagRequestDTO tagDTO) {
        return converter.convertOneToDTO(tagDAO.create(converter.convertDTOtoModel(tagDTO)));
    }

    @Override
    public List<TagResponseDTO> getAllTags() {
        return converter.convert(tagDAO.getAllTags());
    }

    @Override
    public void deleteById(int id) {
        tagDAO.deleteById(id);
    }

    @Override
    public TagResponseDTO getById(int id) {
        return converter.convertOneToDTO(tagDAO.getById(id));
    }

    public List<TagResponseDTO> getByName(String name) {
        return converter.convert(tagDAO.getByName(name));
    }
}
