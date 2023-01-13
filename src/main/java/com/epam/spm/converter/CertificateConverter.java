package com.epam.spm.converter;

import com.epam.spm.dto.RequestCertificateDTO;
import com.epam.spm.dto.ResponseCertificateDTO;

import java.util.List;

public interface CertificateConverter {
    List<ResponseCertificateDTO> convertToDTO(List<com.epam.spm.model.GiftCertificate> dto);

    ResponseCertificateDTO convertOneToDTO(com.epam.spm.model.GiftCertificate certificate);
    ResponseCertificateDTO convertRequestToResponse(RequestCertificateDTO certificate);
}
