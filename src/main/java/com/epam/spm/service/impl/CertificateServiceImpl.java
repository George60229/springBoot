package com.epam.spm.service.impl;

import com.epam.spm.converter.CertificateConverter;
import com.epam.spm.dao.CertificateDAO;
import com.epam.spm.dto.request.CertificateRequestDTO;
import com.epam.spm.dto.response.ResponseCertificateDTO;
import com.epam.spm.exception.AppNotFoundException;
import com.epam.spm.exception.ErrorCode;
import com.epam.spm.model.GiftCertificate;
import com.epam.spm.service.CertificateService;
import com.epam.spm.utils.SortParameter;
import com.epam.spm.utils.SortWay;
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
    public List<ResponseCertificateDTO> listCertificates(SortParameter parameter, SortWay sortWay) {

        List<GiftCertificate> certificateDTOList = certificateDAO.listItems(parameter,sortWay);

        return converter.convertListToDTO(certificateDTOList);
    }

    @Override
    public void deleteCertificateById(Integer id) {
        certificateDAO.deleteById(id);
    }

    @Override
    public boolean deleteCertificateByName(String name) {
        return certificateDAO.deleteByName(name);
    }

    @Override
    public List<ResponseCertificateDTO> findByTagName(String tagName) {
        return converter.convertListToDTO(certificateDAO.findByTagName(tagName));
    }

    @Override
    public ResponseCertificateDTO getCertificateById(int id) {

        if(certificateDAO.getCertificateById(id).isEmpty()){
            throw new AppNotFoundException("Certificate with this id "+id+" is not found", ErrorCode.CERTIFICATE_NOT_FOUND);
        }
        GiftCertificate giftCertificate=certificateDAO.getCertificateById(id).get();

        return converter.convertToDTO(giftCertificate);
    }

    @Override
    public ResponseCertificateDTO editById(int id, CertificateRequestDTO certificateDTO) {

        if (certificateDAO.getCertificateById(id).isEmpty()) {
            throw new AppNotFoundException("Certificate with this id is not found: " + id, ErrorCode.CERTIFICATE_NOT_FOUND);
        }

        GiftCertificate giftCertificate = certificateDAO.getCertificateById(id).get();

        return converter.convertToDTO(certificateDAO.update(converter.updateByRequest(giftCertificate, certificateDTO)));//todo updateByRequest
    }

    @Override
    public ResponseCertificateDTO createCertificate(CertificateRequestDTO certificateDTO) {
        return converter.convertToDTO(certificateDAO.createCertificate(converter.convertDTOtoModel(certificateDTO)));
    }

    @Override
    public List<ResponseCertificateDTO> getCertificateByName(String name) {
        return converter.convertListToDTO(certificateDAO.getEntityByName(name));
    }

    @Override
    public List<ResponseCertificateDTO> getCertificateByDescription(String description) {
        return converter.convertListToDTO(certificateDAO.getCertificateByDescription(description));
    }


}
