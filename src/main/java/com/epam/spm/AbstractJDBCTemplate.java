package com.epam.spm;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

abstract public class AbstractJDBCTemplate {
    protected DataSource dataSource;
    protected JdbcTemplate jdbcTemplateObject;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }
}
