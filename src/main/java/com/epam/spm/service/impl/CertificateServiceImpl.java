package com.epam.spm.service.impl;

import com.epam.spm.converter.CertificateConverter;
import com.epam.spm.dao.CertificateDAO;
import com.epam.spm.dto.RequestCertificateDTO;
import com.epam.spm.dto.ResponseCertificateDTO;
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
    public List<ResponseCertificateDTO> listCertificates() {
        List<GiftCertificate> certificateDTOList = certificateDAO.listItems();

        return converter.convertToDTO(removeSame(certificateDTOList));
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
        return converter.convertToDTO(removeSame(certificateDAO.findByTagName(tagName)));
    }

    @Override
    public ResponseCertificateDTO getCertificateById(int id) {
        return converter.convertOneToDTO(certificateDAO.getCertificateById(id));
    }

    @Override
    public List<ResponseCertificateDTO> listItemsDESC() {
        return converter.convertToDTO(removeSame(certificateDAO.listItemsDESC()));
    }

    @Override
    public List<ResponseCertificateDTO> getEntityByDescription(String description) {
        return converter.convertToDTO(removeSame(certificateDAO.getEntityByDescription(description)));
    }

    @Override
    public List<ResponseCertificateDTO> listItemsDateASC() {
        return converter.convertToDTO(removeSame(certificateDAO.listItemsDateASC()));
    }

    @Override
    public List<ResponseCertificateDTO> listItemsDateDESC() {
        return converter.convertToDTO(removeSame(certificateDAO.listItemsDateDESC()));
    }

    @Override
    public ResponseCertificateDTO editById(int id, RequestCertificateDTO certificate) {
        return converter.convertOneToDTO(certificateDAO.editById(id, certificate));
    }

    @Override
    public ResponseCertificateDTO createCertificate(RequestCertificateDTO certificateDTO) {
        return converter.convertOneToDTO(certificateDAO.createCertificate(certificateDTO));
    }

    @Override
    public List<ResponseCertificateDTO> getCertificateByName(String name) {
        return converter.convertToDTO(removeSame(certificateDAO.getEntityByName(name)));
    }

    private List<GiftCertificate> removeSame(List<GiftCertificate> certificates) {

        List<GiftCertificate> result = new ArrayList<>();
        Set<Integer> idList = new HashSet<>();
        certificates.sort(Comparator.comparing(GiftCertificate::getId));

        for (GiftCertificate certificate : certificates) {
            if (!idList.contains(certificate.getId())) {
                idList.add(certificate.getId());
                result.add(certificate);
            } else {
                int res = idList.size() - 1;
                GiftCertificate newCertificate = result.get(res);
                result.remove(res);
                newCertificate.addTag(certificate.getTags().get(0));
                result.add(newCertificate);
            }
        }
        return result;
    }


}
