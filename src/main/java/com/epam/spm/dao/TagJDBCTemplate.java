package com.epam.spm.dao;


import com.epam.spm.model.EntityDAO;
import com.epam.spm.model.Tag;
import com.epam.spm.mapper.TagMapper;

import java.util.List;
import java.util.Optional;

public class TagJDBCTemplate extends AbstractJDBCTemplate implements EntityDAO<Tag> {
    public Tag create(Tag tag) {
        String SQL = "insert into tages (name) values (?)";

        jdbcTemplateObject.update(SQL, tag.getName());
return tag;

    }

    @Override
    public Tag getEntityByName(String name) {
        String SQL = "select * from tages where name='" + name + "'";
        List<Tag> result = jdbcTemplateObject.query(SQL, new TagMapper());
        //todo optional for one object

        return result.get(0);
    }

    @Override
    public List<Tag> listItems() {
        String SQL = "select * from tages";
        return jdbcTemplateObject.query(SQL, new TagMapper());

    }

    @Override
    public boolean deleteById(Integer id) {
        String SQL = "delete from tages where tag_id=" + id;
        return jdbcTemplateObject.update(SQL) > 0;
    }

    @Override
    public boolean deleteByName(String name) {
        String SQL = "delete from tages where name='" + name + "'";
        return jdbcTemplateObject.update(SQL) > 0;
    }
}
