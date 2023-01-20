package com.epam.esm.config;

import com.epam.esm.dao.CertificateDAO;
import com.epam.esm.dao.TagDAO;
import com.epam.esm.dao.impl.CertificateDAOImpl;
import com.epam.esm.dao.impl.TagDAOImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.DataSourceFactory;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class AppConfig {
    @Value(value = "${spring.datasource.url}")
    private String url;



    @Bean
    public CertificateDAO certificateDAO() {
        System.out.println(url);
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        DataSource dataSource = context.getBean("dataSource", DataSource.class);
        return new CertificateDAOImpl(dataSource);
    }

    @Bean
    public TagDAO tagDAO() {

        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        DataSource dataSource = context.getBean("dataSource", DataSource.class);
        return new TagDAOImpl(dataSource);
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(){
        return new PropertySourcesPlaceholderConfigurer();
    }
    @Bean
    public DataSource dataSource() throws SQLException {

        return null;
    }

}
