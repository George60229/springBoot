package com.epam.spm.service.impl;

import com.epam.spm.converter.CertificateConverter;
import com.epam.spm.dao.CertificateDAO;
import com.epam.spm.dto.request.CertificateFindByDTO;
import com.epam.spm.dto.request.CertificateRequestDTO;
import com.epam.spm.dto.response.ResponseCertificateDTO;
import com.epam.spm.exception.AppNotFoundException;
import com.epam.spm.exception.ErrorCode;
import com.epam.spm.model.GiftCertificate;
import com.epam.spm.service.CertificateService;
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
    public void deleteCertificateById(Integer id) {
        certificateDAO.deleteById(id);
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
    public List<ResponseCertificateDTO> listCertificates(CertificateFindByDTO certificateFindByDTO) {
        if (certificateFindByDTO == null) {
            certificateFindByDTO = new CertificateFindByDTO();
        }
        return converter.convertListToDTO(certificateDAO.listItems(certificateFindByDTO));
    }


}
