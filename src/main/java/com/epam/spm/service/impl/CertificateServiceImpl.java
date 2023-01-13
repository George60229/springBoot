package com.epam.spm.service.impl;

import com.epam.spm.converter.CertificateConverter;
import com.epam.spm.converter.impl.CertificateConverterImpl;
import com.epam.spm.dao.CertificateDAO;
import com.epam.spm.dto.RequestCertificateDTO;
import com.epam.spm.dto.ResponseCertificateDTO;
import com.epam.spm.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CertificateServiceImpl implements CertificateService {
    @Autowired
    private CertificateDAO certificateDAO;


    @Override
    public List<ResponseCertificateDTO> listCertificates() {
        return certificateDAO.listItems();
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
        return certificateDAO.findByTagName(tagName);
    }

    @Override
    public ResponseCertificateDTO getCertificateById(int id) {
        return certificateDAO.getCertificateById(id);
    }

    @Override
    public List<ResponseCertificateDTO> listItemsDESC() {
        return certificateDAO.listItemsDESC();
    }

    @Override
    public List<ResponseCertificateDTO> getEntityByDescription(String description) {
        return certificateDAO.getEntityByDescription(description);
    }

    @Override
    public List<ResponseCertificateDTO> listItemsDateASC() {
        return certificateDAO.listItemsDateASC();
    }

    @Override
    public List<ResponseCertificateDTO> listItemsDateDESC() {
        return certificateDAO.listItemsDateDESC();
    }

    @Override
    public ResponseCertificateDTO editById(int id, RequestCertificateDTO certificate) {
        return certificateDAO.editById(id, certificate);
    }

    @Override
    public ResponseCertificateDTO createCertificate(RequestCertificateDTO certificateDTO) {
        return certificateDAO.createCertificate(certificateDTO);
    }

    @Override
    public List<ResponseCertificateDTO> getCertificateByName(String name) {
        return certificateDAO.getEntityByName(name);
    }
}
