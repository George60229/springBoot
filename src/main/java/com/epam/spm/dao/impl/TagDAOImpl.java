package com.epam.spm.dao.impl;


import com.epam.spm.dao.TagDAO;
import com.epam.spm.exception.AppNotFoundException;
import com.epam.spm.exception.ErrorCode;
import com.epam.spm.mapper.TagMapper;
import com.epam.spm.model.Tag;
import com.epam.spm.dao.enumeration.CertificateParameters;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TagDAOImpl extends EntityDAOImpl implements TagDAO {


    public TagDAOImpl(DataSource dataSource) {
        setDataSource(dataSource);
    }

    public Tag create(Tag tag) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("tages").usingGeneratedKeyColumns("tag_id");
        Map<String, Object> parameters = new HashMap<>(1);
        parameters.put(CertificateParameters.NAME.getMessage(), tag.getName());
        int newId = (int) simpleJdbcInsert.executeAndReturnKey(parameters);
        Tag result = new Tag();
        result.setName(tag.getName());
        result.setId(newId);
        return result;
    }

    @Override
    public List<Tag> getAllTags() {
        String SQL = "select * from tages order by name";
        return jdbcTemplateObject.query(SQL, new TagMapper());
    }

    @Override
    public Tag getById(int id) {
        String SQL = "select * from tages where tag_id=" + id;
        return jdbcTemplateObject.queryForObject(SQL, new TagMapper());
    }

    @Override
    public List<Tag> getByName(String name) {
        String SQL = "select * from tages where name='" + name + "'";
        return jdbcTemplateObject.query(SQL, new TagMapper());
    }

    @Override
    public boolean deleteById(Integer id) {
        String sql="delete from certificates_to_tages where tag_id="+id;
        jdbcTemplateObject.update(sql);
        String SQL = "delete from tages where tag_id=" + id;

        return jdbcTemplateObject.update(SQL)>0;
    }

}
