package com.epam.spm.controller;

import com.epam.spm.Gift_certificate;
import com.epam.spm.JDBC.CertificatesJDBCTemplate;
import com.epam.spm.JDBC.TagJDBCTemplate;
import com.epam.spm.Tag;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.util.List;


@RestController
public class CertificateController {
    @GetMapping("/getCertificate")
    public String getTag(@RequestParam String name) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        DataSource dataSource = context.getBean("dataSource", DataSource.class);
        CertificatesJDBCTemplate certificatesJDBCTemplate = new CertificatesJDBCTemplate();
        certificatesJDBCTemplate.setDataSource(dataSource);
        return certificatesJDBCTemplate.getEntityByName(name).toString();
    }

    @PostMapping("/addCertificate")
    public void addTag(@RequestParam Gift_certificate giftCertificate) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        DataSource dataSource = context.getBean("dataSource", DataSource.class);
        CertificatesJDBCTemplate certificatesJDBCTemplate = new CertificatesJDBCTemplate();
        certificatesJDBCTemplate.setDataSource(dataSource);
        certificatesJDBCTemplate.create(giftCertificate.getName(), giftCertificate.getPrice());
    }

    @GetMapping("/getAllCertificate")
    public StringBuilder getAllTags() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        DataSource dataSource = context.getBean("dataSource", DataSource.class);
        CertificatesJDBCTemplate certificatesJDBCTemplate = new CertificatesJDBCTemplate();
        certificatesJDBCTemplate.setDataSource(dataSource);
        List<Gift_certificate> result = certificatesJDBCTemplate.listItems();
        StringBuilder list = new StringBuilder();
        for (Gift_certificate certificate : result) {
            list.append(certificate.toString()).append("||\n");
        }
        return list;
    }
}
