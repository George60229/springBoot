package com.epam.spm.converter;

import com.epam.spm.dto.request.CertificateRequestDTO;
import com.epam.spm.dto.response.ResponseCertificateDTO;
import com.epam.spm.model.GiftCertificate;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Component
public class CertificateConverter {


    public List<ResponseCertificateDTO> convertListToDTO(List<GiftCertificate> certificates) {

        List<ResponseCertificateDTO> responseCertificateDTOList = new LinkedList<>();

        for (GiftCertificate certificate : certificates) {
            responseCertificateDTOList.add(convertToDTO(certificate));
        }
        //stream hear
        return responseCertificateDTOList;
        //controller->service->dao
    }


    public ResponseCertificateDTO convertToDTO(GiftCertificate certificate) {
        ResponseCertificateDTO responseCertificateDTO = new ResponseCertificateDTO();
        responseCertificateDTO.setCertificateId(certificate.getId());
        responseCertificateDTO.setName(certificate.getName());
        responseCertificateDTO.setDescription(certificate.getDescription());
        responseCertificateDTO.setDuration(certificate.getDuration());
        responseCertificateDTO.setTags(certificate.getTags());
        if (certificate.getCreateDate() != null) {
            responseCertificateDTO.setCreateDate(certificate.getCreateDate().toString());
        }
        if (certificate.getLastUpdateDate() != null) {
            responseCertificateDTO.setLastUpdateDate(certificate.getLastUpdateDate().toString());
        }

        responseCertificateDTO.setPrice(certificate.getPrice());
        return responseCertificateDTO;
    }

    public GiftCertificate convertDTOtoModel(CertificateRequestDTO certificate) {
        GiftCertificate giftCertificate = new GiftCertificate();
        giftCertificate.setName(certificate.getName());
        giftCertificate.setDescription(certificate.getDescription());
        giftCertificate.setDuration(certificate.getDuration());
        giftCertificate.setPrice(certificate.getPrice());
        giftCertificate.setTags(certificate.getTags());
        return giftCertificate;
    }

    public GiftCertificate updateByRequest(GiftCertificate giftCertificate, CertificateRequestDTO certificateRequestDTO) {
        giftCertificate.setName(Objects.requireNonNullElse(certificateRequestDTO.getName(), giftCertificate.getName()));
        giftCertificate.setPrice(Objects.requireNonNullElse(certificateRequestDTO.getPrice(), giftCertificate.getPrice()));
        giftCertificate.setDescription(Objects.requireNonNullElse(certificateRequestDTO.getDescription(), giftCertificate.getDescription()));
        if (giftCertificate.getDuration() == 0) {
            giftCertificate.setDuration(certificateRequestDTO.getDuration());
        }
        if(certificateRequestDTO.getTags()!=null){
            giftCertificate.setTags(certificateRequestDTO.getTags());
        }
        return giftCertificate;


    }


}
