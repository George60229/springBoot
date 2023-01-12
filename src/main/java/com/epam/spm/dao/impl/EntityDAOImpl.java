package com.epam.spm.dao.impl;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

abstract public class EntityDAOImpl {

    protected DataSource dataSource;
    protected JdbcTemplate jdbcTemplateObject;


    protected void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }


}
