package com.epam.esm.dao.impl;

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
