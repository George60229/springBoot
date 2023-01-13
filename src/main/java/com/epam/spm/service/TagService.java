package com.epam.spm.service;

import com.epam.spm.dto.RequestCertificateDTO;
import com.epam.spm.dto.RequestTagDTO;
import com.epam.spm.dto.ResponseCertificateDTO;
import com.epam.spm.dto.ResponseTagDTO;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface TagService {
    List<ResponseTagDTO> getTagByName(String name);
    ResponseTagDTO createCertificate(@RequestBody RequestTagDTO tagDTO);
    List<ResponseTagDTO> getAllTags();


}
