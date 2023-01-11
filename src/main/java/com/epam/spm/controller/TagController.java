package com.epam.spm.controller;


import com.epam.spm.dto.CreateTagDTO;
import com.epam.spm.dto.TagDTO;
import com.epam.spm.model.AddDataSource;
import com.epam.spm.dao.TagDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.util.List;

@RestController
public class TagController implements AddDataSource {



    TagDAOImpl tagDAO;
    @GetMapping("/getTag")
    public String getTag(@RequestParam String name) {


        return tagDAO.getEntityByName(name).getName();
    }

    @PostMapping("/addTag")
    public CreateTagDTO addTag(@RequestBody CreateTagDTO tag) {
        TagDAOImpl tagJDBCTemplate=getDatasource();

        tagJDBCTemplate.create(tag);
        return tag;
    }

    @GetMapping("/getAllTags")
    public StringBuilder getAllTags() {
        TagDAOImpl tagJDBCTemplate=getDatasource();
        List<TagDTO> result = tagJDBCTemplate.listItems();
        StringBuilder list = new StringBuilder();
        for (TagDTO tag : result) {
            list.append(tag.getName()).append("||");
        }
        return list;
    }
    @Override
    public TagDAOImpl getDatasource(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        DataSource dataSource = context.getBean("dataSource", DataSource.class);
        return new TagDAOImpl(dataSource);
    }
}
