package com.epam.spm.config;
import com.epam.spm.dao.impl.CertificateDAOImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        CertificateDAOImpl certificateDAO = context.getBean("certificateDAO", CertificateDAOImpl.class);
        System.out.println(certificateDAO);



    }
}
