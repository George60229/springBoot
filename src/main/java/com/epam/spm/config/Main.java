package com.epam.spm.config;
import com.epam.spm.dao.CertificateDAO;
import com.epam.spm.dao.impl.CertificateDAOImpl;
import org.h2.jdbcx.JdbcDataSource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        CertificateDAOImpl certificateDAO = context.getBean("certificateDAO", CertificateDAOImpl.class);
        System.out.println(certificateDAO);





    }
}
