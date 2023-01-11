package com.epam.spm.controller;


import com.epam.spm.dto.CertificateDTO;
import com.epam.spm.dto.CreateCertificateDTO;
import com.epam.spm.model.AddDataSource;

import com.epam.spm.dao.CertificateDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class CertificateController implements AddDataSource {

    @Autowired
    CertificateDAOImpl certificateDAO;


    @GetMapping("/getCertificate")
    public String getCertificate(@RequestParam String name) {

        return certificateDAO.getEntityByName(name).toString();
    }

    @PostMapping("/addCertificate")
    @ResponseStatus(HttpStatus.CREATED)
    public CreateCertificateDTO addCertificate(@RequestBody CreateCertificateDTO giftCertificate) {


        return certificateDAO.create(giftCertificate);
    }

    @GetMapping("/getAllCertificate")
    public StringBuilder getAllCertificate() {

        List<CertificateDTO> result = certificateDAO.listItems();
        StringBuilder list = new StringBuilder();
        for (CertificateDTO certificate : result) {
            list.append(certificate.toString()).append("||\n");
        }
        return list;
    }

    @DeleteMapping("/certificate")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCertificateById(@RequestParam Integer id) {

        if (!certificateDAO.deleteById(id)) {
            throw new IllegalArgumentException("Certificate with this id is not exist");
        }

    }

    @PatchMapping("/editCertificate")
    @ResponseStatus(HttpStatus.OK)
    public void editCertificateById(@RequestParam Integer id, @RequestBody CreateCertificateDTO certificate) {

        certificateDAO.editById(id, certificate);
    }

    @GetMapping("/getAllCertificateDESC")
    public StringBuilder getAllCertificateDESC() {

        List<CertificateDTO> result = certificateDAO.listItemsDESC();
        StringBuilder list = new StringBuilder();
        for (CertificateDTO certificate : result) {
            list.append(certificate.toString()).append("||\n");
        }
        return list;
    }

    @GetMapping("/findByTagName")
    public StringBuilder getTagNyName(@RequestParam String tagName) {

        List<CertificateDTO> result = certificateDAO.findByTagName(tagName);
        StringBuilder list = new StringBuilder();
        for (CertificateDTO certificate : result) {
            list.append(certificate.toString()).append("||\n");
        }
        return list;
    }

    @Override
    public CertificateDAOImpl getDatasource() {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        return context.getBean("certificateDAO", CertificateDAOImpl.class);
    }
}
