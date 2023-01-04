package com.epam.spm.JDBC;


import com.epam.spm.EntityDAO;
import com.epam.spm.model.Tag;
import com.epam.spm.mapper.TagMapper;

import java.util.List;

public class TagJDBCTemplate extends AbstractJDBCTemplate implements EntityDAO<Tag> {
    public void create(String name) {
        String SQL = "insert into tages (name) values (?)";

        jdbcTemplateObject.update(SQL, name);
        System.out.println("Created Record Name = " + name);

    }

    @Override
    public Tag getEntityByName(String name) {
        String SQL = "select * from tages where name='" + name+"'";
        List<Tag> result=jdbcTemplateObject.query(SQL, new TagMapper());
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
        return jdbcTemplateObject.update(SQL)>0;
    }

@Override
    public boolean deleteByName(String name) {
        String SQL = "delete from tages where name='" + name+"'";
        return jdbcTemplateObject.update(SQL)>0;
    }
}
