package com.epam.spm.converter;

import com.epam.spm.dto.CertificateDTO;
import com.epam.spm.model.GiftCertificate;

import java.util.List;

public interface CertificateService {
    List<CertificateDTO> convertToDTO(List<GiftCertificate> dto);

    CertificateDTO convertOneToDTO(GiftCertificate certificate);
}
