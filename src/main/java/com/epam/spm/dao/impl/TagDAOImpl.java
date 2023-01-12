package com.epam.spm.dao.impl;


import com.epam.spm.dao.TagDAO;
import com.epam.spm.dto.CreateTagDTO;
import com.epam.spm.dto.TagDTO;
import com.epam.spm.exception.TagNotFoundException;
import com.epam.spm.mapper.TagMapper;
import com.epam.spm.converter.TagService;
import com.epam.spm.converter.TagServiceImpl;
import javax.sql.DataSource;
import java.util.List;

public class TagDAOImpl extends EntityDAOImpl implements TagDAO {
    final TagService tagService=new TagServiceImpl();

    public TagDAOImpl(DataSource dataSource) {
        setDataSource(dataSource);
    }

    public CreateTagDTO create(CreateTagDTO tag) {
        String SQL = "insert into tages (name) values (?)";

        jdbcTemplateObject.update(SQL, tag.getName());
        return tag;

    }




    @Override
    public List<TagDTO> getEntityByName(String name) {
        String SQL = "select * from tages where name='" + name + "'";
        List<TagDTO> result = tagService.convert(jdbcTemplateObject.query(SQL, new TagMapper()));
        if (result.size() == 0) {
            throw new TagNotFoundException("Tag with this name is not found" + name);
        }
        return result;
    }

    @Override
    public List<TagDTO> listItems() {
        String SQL = "select * from tages order by name ASC";
        return tagService.convert(jdbcTemplateObject.query(SQL, new TagMapper()));

    }


    @Override
    public boolean deleteById(Integer id) {
        String SQL = "delete from tages where tag_id=" + id;
        if (jdbcTemplateObject.update(SQL) == 0) {
            throw new TagNotFoundException("Tag with this id " + id + " is not deleted ");
        }
        return true;
    }

    @Override
    public boolean deleteByName(String name) {
        String SQL = "delete from tages where name='" + name + "'";
        if (jdbcTemplateObject.update(SQL) == 0) {
            throw new TagNotFoundException("Tag with this name " + name + " is not deleted ");
        }
        return true;
    }
}
