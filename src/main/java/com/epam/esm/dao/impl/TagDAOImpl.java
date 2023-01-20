package com.epam.esm.dao.impl;


import com.epam.esm.dao.TagDAO;
import com.epam.esm.mapper.TagMapper;
import com.epam.esm.model.Tag;
import com.epam.esm.dao.enumeration.CertificateParameters;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    public Optional<Tag> getById(int id) {
        String SQL = "select * from tages where tag_id=" + id;
        return Optional.ofNullable(jdbcTemplateObject.queryForObject(SQL, new TagMapper()));
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
