package com.epam.spm.service;

import com.epam.spm.dto.RequestTagDTO;
import com.epam.spm.dto.ResponseTagDTO;
import com.epam.spm.model.GiftCertificate;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface TagService {
    List<ResponseTagDTO> getTagByName(String name);
    ResponseTagDTO createCertificate(@RequestBody RequestTagDTO tagDTO);
    List<ResponseTagDTO> getAllTags();


}
