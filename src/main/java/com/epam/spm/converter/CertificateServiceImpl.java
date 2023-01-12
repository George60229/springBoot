package com.epam.spm.converter;

import com.epam.spm.dto.CertificateDTO;
import com.epam.spm.model.GiftCertificate;

import java.util.*;

public class CertificateServiceImpl implements CertificateService {


    @Override
    public List<CertificateDTO> convertToDTO(List<GiftCertificate> certificates) {

        List<CertificateDTO> certificateDTOList = new ArrayList<>();

        for (GiftCertificate certificate : certificates) {


            certificateDTOList.add(convertOneToDTO(certificate));

        }

        return certificateDTOList;
        //controller->service->dao
    }

    @Override
    public CertificateDTO convertOneToDTO(GiftCertificate certificate) {
        CertificateDTO giftCertificate = new CertificateDTO();
        giftCertificate.setCertificateId(certificate.getId());
        giftCertificate.setName(certificate.getName());
        giftCertificate.setDescription(certificate.getDescription());
        giftCertificate.setDuration(certificate.getDuration());
        giftCertificate.setTags(certificate.getTags());
        if (certificate.getCreateDate() != null) {
            giftCertificate.setCreateDate(certificate.getCreateDate().toString());
        }
        if (certificate.getLastUpdateDate() != null) {
            giftCertificate.setLastUpdateDate(certificate.getLastUpdateDate().toString());
        }

        giftCertificate.setPrice(certificate.getPrice());
        return giftCertificate;
    }


}
