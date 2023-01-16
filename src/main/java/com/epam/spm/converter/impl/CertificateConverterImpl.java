package com.epam.spm.converter.impl;

import com.epam.spm.converter.CertificateConverter;
import com.epam.spm.dto.RequestCertificateDTO;
import com.epam.spm.dto.ResponseCertificateDTO;
import com.epam.spm.model.GiftCertificate;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Component
public class CertificateConverterImpl implements CertificateConverter {


    @Override
    public List<ResponseCertificateDTO> convertToDTO(List<GiftCertificate> certificates) {

        List<ResponseCertificateDTO> responseCertificateDTOList = new LinkedList<>();

        for (com.epam.spm.model.GiftCertificate certificate : certificates) {
            responseCertificateDTOList.add(convertOneToDTO(certificate));
        }
        return responseCertificateDTOList;
        //controller->service->dao
    }

    @Override
    public ResponseCertificateDTO convertOneToDTO(GiftCertificate certificate) {
        ResponseCertificateDTO responseCertificateDTO = new ResponseCertificateDTO();
        responseCertificateDTO.setCertificateId(certificate.getId());
        responseCertificateDTO.setName(certificate.getName());
        responseCertificateDTO.setDescription(certificate.getDescription());
        responseCertificateDTO.setDuration(certificate.getDuration());
        responseCertificateDTO.setTags(certificate.getTags());
        responseCertificateDTO.setCreateDate(certificate.getCreateDate());
        responseCertificateDTO.setLastUpdateDate(certificate.getLastUpdateDate());
        responseCertificateDTO.setPrice(certificate.getPrice());
        return responseCertificateDTO;
    }




}
