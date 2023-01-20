package com.epam.esm.service.impl;

import com.epam.esm.converter.CertificateConverter;
import com.epam.esm.dao.CertificateDAO;
import com.epam.esm.dto.request.CertificateFindByDTO;
import com.epam.esm.dto.request.CertificateRequestDTO;
import com.epam.esm.dto.response.ResponseCertificateDTO;
import com.epam.esm.exception.AppNotFoundException;
import com.epam.esm.exception.ErrorCode;
import com.epam.esm.model.GiftCertificate;
import com.epam.esm.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CertificateServiceImpl implements CertificateService {
    @Autowired
    private CertificateDAO certificateDAO;
    @Autowired
    CertificateConverter converter;


    @Override
    public boolean deleteCertificateById(Integer id) {
        return certificateDAO.deleteById(id);
    }



    @Override
    public List<ResponseCertificateDTO> findByTagName(String tagName) {
        return converter.convertListToDTO(certificateDAO.findByTagName(tagName));
    }

    @Override
    public ResponseCertificateDTO getCertificateById(int id) {

        if (certificateDAO.getCertificateById(id).isEmpty()) {
            throw new AppNotFoundException("Certificate with this id " + id + " is not found",
                    ErrorCode.CERTIFICATE_NOT_FOUND);
        }
        GiftCertificate giftCertificate = certificateDAO.getCertificateById(id).get();

        return converter.convertToDTO(giftCertificate);
    }

    @Override
    public ResponseCertificateDTO editById(int id, CertificateRequestDTO certificateDTO) {

        Optional<GiftCertificate> certificate = certificateDAO.getCertificateById(id);
        if (certificate.isEmpty()) {
            throw new AppNotFoundException("Certificate with this id is not found: " + id,
                    ErrorCode.CERTIFICATE_NOT_FOUND);
        }

        GiftCertificate giftCertificate = certificate.get();
        return converter.convertToDTO(certificateDAO.update(converter.updateByRequest(giftCertificate, certificateDTO)));
    }

    @Override
    public ResponseCertificateDTO createCertificate(CertificateRequestDTO certificateDTO) {
        return converter.convertToDTO(certificateDAO.createCertificate(converter.convertDTOtoModel(certificateDTO)));
    }


    @Override
    public void setAll(CertificateDAO certificateDAO, CertificateConverter tagConverter) {
        this.converter=tagConverter;
        this.certificateDAO=certificateDAO;
    }

    @Override
    public List<ResponseCertificateDTO> listCertificates(CertificateFindByDTO certificateFindByDTO) {
        if (certificateFindByDTO == null) {
            certificateFindByDTO = new CertificateFindByDTO();
        }
        return converter.convertListToDTO(certificateDAO.listItems(certificateFindByDTO));
    }


}
