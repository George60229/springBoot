package DAO.impl;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;

public class ConfigFotTest {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    DataSource dataSourceH2 = context.getBean("h2Source", DataSource.class);
}
