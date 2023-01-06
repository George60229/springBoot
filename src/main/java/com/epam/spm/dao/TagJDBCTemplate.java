package com.epam.spm.dao;


import com.epam.spm.model.EntityDAO;
import com.epam.spm.model.Tag;
import com.epam.spm.mapper.TagMapper;
import com.epam.spm.model.TagDAO;

import java.util.List;
import java.util.Optional;

public class TagJDBCTemplate extends AbstractJDBCTemplate implements TagDAO {
    public TagJDBCTemplate() {
        super();
    }

    public Tag create(Tag tag) {
        String SQL = "insert into tages (name) values (?)";

        jdbcTemplateObject.update(SQL, tag.getName());
        return tag;

    }

    @Override
    public Tag getEntityByName(String name) {
        String SQL = "select * from tages where name='" + name + "'";
        List<Tag> result = jdbcTemplateObject.query(SQL, new TagMapper());
        if (result.size() == 0) {
            throw new IllegalArgumentException("Tag with this name is not found" + name);
        }

        return result.get(0);
    }

    @Override
    public List<Tag> listItems() {
        String SQL = "select * from tages order by ASC";
        return jdbcTemplateObject.query(SQL, new TagMapper());

    }


    @Override
    public boolean deleteById(Integer id) {
        String SQL = "delete from tages where tag_id=" + id;
        if (jdbcTemplateObject.update(SQL) == 0) {
            throw new IllegalArgumentException("Tag with this id " + id + " is not deleted ");
        }
        return true;
    }

    @Override
    public boolean deleteByName(String name) {
        String SQL = "delete from tages where name='" + name + "'";
        if (jdbcTemplateObject.update(SQL) == 0) {
            throw new IllegalArgumentException("Tag with this name " + name + " is not deleted ");
        }
        return true;
    }
}
