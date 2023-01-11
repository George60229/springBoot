package com.epam.spm.controller;


import com.epam.spm.dto.CertificateDTO;
import com.epam.spm.dto.CreateCertificateDTO;
import com.epam.spm.model.AddDataSource;

import com.epam.spm.dao.CertificateDAOImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.util.List;


@RestController
public class CertificateController implements AddDataSource {


    @GetMapping("/getCertificate")
    public String getCertificate(@RequestParam String name) {
        CertificateDAOImpl certificatesJDBCTemplate = getDatasource();
        return certificatesJDBCTemplate.getEntityByName(name).toString();
    }

    @PostMapping("/addCertificate")
    @ResponseStatus(HttpStatus.CREATED)
    public CreateCertificateDTO addCertificate(@RequestBody CreateCertificateDTO giftCertificate) {
        CertificateDAOImpl certificatesJDBCTemplate = getDatasource();
        return certificatesJDBCTemplate.create(giftCertificate);
    }

    @GetMapping("/getAllCertificate")
    public StringBuilder getAllCertificate() {
        CertificateDAOImpl certificatesJDBCTemplate = getDatasource();
        List<CertificateDTO> result = certificatesJDBCTemplate.listItems();
        StringBuilder list = new StringBuilder();
        for (CertificateDTO certificate : result) {
            list.append(certificate.toString()).append("||\n");
        }
        return list;
    }

    @DeleteMapping("/certificate")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCertificateById(@RequestParam Integer id) {
        CertificateDAOImpl certificatesJDBCTemplate = getDatasource();
        if (!certificatesJDBCTemplate.deleteById(id)) {
            throw new IllegalArgumentException("Certificate with this id is not exist");
        }

    }

    @PatchMapping("/editCertificate")
    @ResponseStatus(HttpStatus.OK)
    public void editCertificateById(@RequestParam Integer id, @RequestBody CreateCertificateDTO certificate) {
        CertificateDAOImpl certificatesJDBCTemplate = getDatasource();
        certificatesJDBCTemplate.editById(id, certificate);
    }

    @GetMapping("/getAllCertificateDESC")
    public StringBuilder getAllCertificateDESC() {
        CertificateDAOImpl certificatesJDBCTemplate = getDatasource();
        List<CertificateDTO> result = certificatesJDBCTemplate.listItemsDESC();
        StringBuilder list = new StringBuilder();
        for (CertificateDTO certificate : result) {
            list.append(certificate.toString()).append("||\n");
        }
        return list;
    }

    @GetMapping("/findByTagName")
    public StringBuilder getTagNyName(@RequestParam String tagName) {
        CertificateDAOImpl certificatesJDBCTemplate = new CertificateDAOImpl();
        List<CertificateDTO> result = certificatesJDBCTemplate.findByTagName(tagName);
        StringBuilder list = new StringBuilder();
        for (CertificateDTO certificate : result) {
            list.append(certificate.toString()).append("||\n");
        }
        return list;
    }

    @Override
    public CertificateDAOImpl getDatasource() {
        CertificateDAOImpl certificatesJDBCTemplate = new CertificateDAOImpl();
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        DataSource dataSource = context.getBean("dataSource", DataSource.class);
        certificatesJDBCTemplate.setDataSource(dataSource);
        return certificatesJDBCTemplate;
    }
}
