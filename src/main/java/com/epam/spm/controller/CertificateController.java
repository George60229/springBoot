package com.epam.spm.controller;

import com.epam.spm.model.GiftCertificate;

import com.epam.spm.dao.CertificatesJDBCTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.util.List;


@RestController
public class CertificateController {


    //todo ioc and d





    @GetMapping("/getCertificate")
    public String getCertificate(@RequestParam String name) {
        CertificatesJDBCTemplate certificatesJDBCTemplate=new CertificatesJDBCTemplate();
        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");

        DataSource dataSource = context.getBean("dataSource", DataSource.class);
        certificatesJDBCTemplate.setDataSource(dataSource);
        return certificatesJDBCTemplate.getEntityByName(name).toString();
    }

    @PostMapping("/addCertificate")
    @ResponseStatus(HttpStatus.CREATED)
    public GiftCertificate addCertificate(@RequestBody GiftCertificate giftCertificate) {
        CertificatesJDBCTemplate certificatesJDBCTemplate=new CertificatesJDBCTemplate();
        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");

        DataSource dataSource = context.getBean("dataSource", DataSource.class);
        certificatesJDBCTemplate.setDataSource(dataSource);
        return certificatesJDBCTemplate.create(giftCertificate);
    }

    @GetMapping("/getAllCertificate")
    public StringBuilder getAllCertificate() {
        CertificatesJDBCTemplate certificatesJDBCTemplate=new CertificatesJDBCTemplate();
        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");

        DataSource dataSource = context.getBean("dataSource", DataSource.class);
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
        CertificatesJDBCTemplate certificatesJDBCTemplate=new CertificatesJDBCTemplate();
        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");

        DataSource dataSource = context.getBean("dataSource", DataSource.class);
        certificatesJDBCTemplate.setDataSource(dataSource);
        if (!certificatesJDBCTemplate.deleteById(id)) {
            throw new IllegalArgumentException("Certificate with this id is not exist");
        }

    }

    @PatchMapping("/editCertificate")
    @ResponseStatus(HttpStatus.OK)
    public void editCertificateById(@RequestParam Integer id, @RequestBody GiftCertificate certificate) {
        CertificatesJDBCTemplate certificatesJDBCTemplate=new CertificatesJDBCTemplate();
        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");

        DataSource dataSource = context.getBean("dataSource", DataSource.class);
        certificatesJDBCTemplate.setDataSource(dataSource);
        certificatesJDBCTemplate.editById(id, certificate);
    }

    @GetMapping("/getAllCertificateDESC")
    public StringBuilder getAllCertificateDESC() {
        CertificatesJDBCTemplate certificatesJDBCTemplate=new CertificatesJDBCTemplate();
        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");

        DataSource dataSource = context.getBean("dataSource", DataSource.class);
        certificatesJDBCTemplate.setDataSource(dataSource);
        List<GiftCertificate> result = certificatesJDBCTemplate.listItemsDESC();
        StringBuilder list = new StringBuilder();
        for (GiftCertificate certificate : result) {
            list.append(certificate.toString()).append("||\n");
        }
        return list;
    }

}
