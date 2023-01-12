package com.epam.spm.config;

import com.epam.spm.dao.impl.CertificateDAOImpl;
import com.epam.spm.dao.impl.TagDAOImpl;
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
    @Bean
    public TagDAOImpl tagDAO() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        DataSource dataSource = context.getBean("dataSource", DataSource.class);
        return new TagDAOImpl(dataSource);
    }
}