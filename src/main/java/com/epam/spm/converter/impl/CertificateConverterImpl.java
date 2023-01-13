package com.epam.spm.converter.impl;

import com.epam.spm.converter.CertificateConverter;
import com.epam.spm.dto.RequestCertificateDTO;
import com.epam.spm.dto.ResponseCertificateDTO;
import com.epam.spm.model.GiftCertificate;

import java.util.ArrayList;
import java.util.List;

public class CertificateConverterImpl implements CertificateConverter {


    @Override
    public List<ResponseCertificateDTO> convertToDTO(List<GiftCertificate> certificates) {

        List<ResponseCertificateDTO> responseCertificateDTOList = new ArrayList<>();

        for (GiftCertificate certificate : certificates) {


            responseCertificateDTOList.add(convertOneToDTO(certificate));

        }

        return responseCertificateDTOList;
        //controller->service->dao
    }

    @Override
    public ResponseCertificateDTO convertOneToDTO(GiftCertificate certificate) {
        ResponseCertificateDTO giftCertificate = new ResponseCertificateDTO();
        giftCertificate.setCertificateId(certificate.getId());
        giftCertificate.setName(certificate.getName());
        giftCertificate.setDescription(certificate.getDescription());
        giftCertificate.setDuration(certificate.getDuration());
        giftCertificate.setTags(certificate.getTags());
        giftCertificate.setCreateDate(certificate.getCreateDate());
        giftCertificate.setLastUpdateDate(certificate.getLastUpdateDate());
        giftCertificate.setPrice(certificate.getPrice());
        return giftCertificate;
    }

    @Override
    public ResponseCertificateDTO convertRequestToResponse(RequestCertificateDTO certificate) {
        ResponseCertificateDTO giftCertificate = new ResponseCertificateDTO();

        //todo
        giftCertificate.setName(certificate.getName());
        giftCertificate.setDescription(certificate.getDescription());
        giftCertificate.setDuration(certificate.getDuration());
        giftCertificate.setPrice(certificate.getPrice());
        return giftCertificate;
    }


}
