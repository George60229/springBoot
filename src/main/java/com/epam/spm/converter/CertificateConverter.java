package com.epam.spm.converter;

import com.epam.spm.dto.RequestCertificateDTO;
import com.epam.spm.dto.ResponseCertificateDTO;
import com.epam.spm.model.GiftCertificate;

import java.util.List;

public interface CertificateConverter {
    List<ResponseCertificateDTO> convertToDTO(List<GiftCertificate> dto);

    ResponseCertificateDTO convertOneToDTO(GiftCertificate certificate);
    ResponseCertificateDTO convertRequestToResponse(RequestCertificateDTO certificate);
}
