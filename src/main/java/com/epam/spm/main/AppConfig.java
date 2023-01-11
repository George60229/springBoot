package com.epam.spm.main;

import com.epam.spm.dao.CertificateDAOImpl;
import com.epam.spm.dao.TagDAOImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;

@Configuration
public class AppConfig {

    @Bean
    public CertificateDAOImpl certificateDAO() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        DataSource dataSource = context.getBean("dataSource", DataSource.class);
        return new CertificateDAOImpl(dataSource);
    }

}
