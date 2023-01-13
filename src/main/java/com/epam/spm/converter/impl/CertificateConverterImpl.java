package com.epam.spm.converter.impl;

import com.epam.spm.converter.CertificateConverter;
import com.epam.spm.dto.RequestCertificateDTO;
import com.epam.spm.dto.ResponseCertificateDTO;

import java.util.ArrayList;
import java.util.List;

public class CertificateConverterImpl implements CertificateConverter {


    @Override
    public List<ResponseCertificateDTO> convertToDTO(List<com.epam.spm.model.GiftCertificate> certificates) {

        List<ResponseCertificateDTO> responseCertificateDTOList = new ArrayList<>();

        for (com.epam.spm.model.GiftCertificate certificate : certificates) {


            responseCertificateDTOList.add(convertOneToDTO(certificate));

        }

        return responseCertificateDTOList;
        //controller->service->dao
    }

    @Override
    public ResponseCertificateDTO convertOneToDTO(com.epam.spm.model.GiftCertificate certificate) {
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

    @Override
    public ResponseCertificateDTO convertRequestToResponse(RequestCertificateDTO certificate) {
        ResponseCertificateDTO responseCertificateDTO = new ResponseCertificateDTO();

        //todo
        responseCertificateDTO.setName(certificate.getName());
        responseCertificateDTO.setDescription(certificate.getDescription());
        responseCertificateDTO.setDuration(certificate.getDuration());
        responseCertificateDTO.setPrice(certificate.getPrice());
        return responseCertificateDTO;
    }


}
