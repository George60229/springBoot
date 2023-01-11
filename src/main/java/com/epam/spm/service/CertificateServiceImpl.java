package com.epam.spm.service;

import com.epam.spm.dto.CertificateDTO;
import com.epam.spm.model.GiftCertificate;

import java.util.ArrayList;
import java.util.List;

public class CertificateServiceImpl implements CertificateService {


    @Override
    public List<CertificateDTO> convertToDTO(List<GiftCertificate> certificates) {
        List<CertificateDTO> certificateDTOList = new ArrayList<>();

        for (GiftCertificate certificate : certificates) {
            CertificateDTO giftCertificate = new CertificateDTO();
            giftCertificate.setCertificateId(certificate.getId());
            giftCertificate.setName(certificate.getName());
            giftCertificate.setDescription(certificate.getDescription());
            giftCertificate.setTags(certificate.getTags());
            giftCertificate.setDuration(certificate.getDuration());
            giftCertificate.setCreateDate(certificate.getCreateDate());
            giftCertificate.setLastUpdateDate(certificate.getLastUpdateDate());
            giftCertificate.setPrice(certificate.getPrice());
            certificateDTOList.add(giftCertificate);
        }

        return certificateDTOList;
    }



}
