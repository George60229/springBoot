package com.epam.spm.controller;

import com.epam.spm.model.GiftCertificate;

import com.epam.spm.JDBC.CertificatesJDBCTemplate;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.util.List;


@RestController

public class CertificateController {
    @GetMapping("/getCertificate")
    public String getCertificate(@RequestParam String name) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        DataSource dataSource = context.getBean("dataSource", DataSource.class);
        CertificatesJDBCTemplate certificatesJDBCTemplate = new CertificatesJDBCTemplate();
        certificatesJDBCTemplate.setDataSource(dataSource);
        return certificatesJDBCTemplate.getEntityByName(name).toString();
    }

    @PostMapping("/addCertificate")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCertificate(@RequestBody GiftCertificate giftCertificate) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        DataSource dataSource = context.getBean("dataSource", DataSource.class);
        CertificatesJDBCTemplate certificatesJDBCTemplate = new CertificatesJDBCTemplate();
        certificatesJDBCTemplate.setDataSource(dataSource);

        certificatesJDBCTemplate.create(giftCertificate);
    }

    @GetMapping("/getAllCertificate")
    public StringBuilder getAllCertificate() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        DataSource dataSource = context.getBean("dataSource", DataSource.class);
        CertificatesJDBCTemplate certificatesJDBCTemplate = new CertificatesJDBCTemplate();
        certificatesJDBCTemplate.setDataSource(dataSource);
        List<GiftCertificate> result = certificatesJDBCTemplate.listItems();
        StringBuilder list = new StringBuilder();
        for (GiftCertificate certificate : result) {
            list.append(certificate.toString()).append("||\n");
        }
        return list;
    }
    @DeleteMapping("/certificate")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCertificateById(@RequestParam Integer id) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        DataSource dataSource = context.getBean("dataSource", DataSource.class);
        CertificatesJDBCTemplate certificatesJDBCTemplate = new CertificatesJDBCTemplate();
        certificatesJDBCTemplate.setDataSource(dataSource);
        certificatesJDBCTemplate.deleteById(id);
    }
    @PatchMapping("/editCertificate")
    @ResponseStatus(HttpStatus.OK)
    public void editCertificateById(@RequestParam Integer id,@RequestBody GiftCertificate certificate) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        DataSource dataSource = context.getBean("dataSource", DataSource.class);
        CertificatesJDBCTemplate certificatesJDBCTemplate = new CertificatesJDBCTemplate();
        certificatesJDBCTemplate.setDataSource(dataSource);
        certificatesJDBCTemplate.editById(id,certificate);
    }
}
