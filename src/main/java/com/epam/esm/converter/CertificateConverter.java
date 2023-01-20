package com.epam.esm.converter;

import com.epam.esm.dto.request.CertificateRequestDTO;
import com.epam.esm.dto.response.ResponseCertificateDTO;
import com.epam.esm.model.GiftCertificate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class CertificateConverter {

    public List<ResponseCertificateDTO> convertListToDTO(List<GiftCertificate> certificates) {

        return certificates.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());


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

    public GiftCertificate updateByRequest(GiftCertificate giftCertificate,
                                           CertificateRequestDTO certificateRequestDTO) {
        if (giftCertificate.getName() != null || certificateRequestDTO.getName() != null) {
            giftCertificate.setName(Objects.requireNonNullElse(certificateRequestDTO.getName(),
                    giftCertificate.getName()));
        }
        if (giftCertificate.getPrice() != null || certificateRequestDTO.getPrice() != null) {
            giftCertificate.setPrice(Objects.requireNonNullElse(certificateRequestDTO.getPrice(),
                    giftCertificate.getPrice()));
        }
        if (giftCertificate.getDescription() != null || certificateRequestDTO.getDescription() != null) {
            giftCertificate.setDescription(Objects.requireNonNullElse(certificateRequestDTO.getDescription(),
                    giftCertificate.getDescription()));
        }
        if (giftCertificate.getDuration() == 0) {
            giftCertificate.setDuration(certificateRequestDTO.getDuration());
        }
        if (certificateRequestDTO.getTags() != null) {
            giftCertificate.setTags(certificateRequestDTO.getTags());
        }
        return giftCertificate;
    }
}
