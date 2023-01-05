package com.epam.spm.controller;


import com.epam.spm.dao.TagJDBCTemplate;
import com.epam.spm.model.Tag;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.util.List;

@RestController
public class TagController {


    @GetMapping("/getTag")
    public String getTag(@RequestParam String name) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        DataSource dataSource = context.getBean("dataSource", DataSource.class);
        TagJDBCTemplate tagJDBCTemplate = new TagJDBCTemplate();
        tagJDBCTemplate.setDataSource(dataSource);
        return tagJDBCTemplate.getEntityByName(name).getName();
    }

    @PostMapping("/addTag")
    public void addTag(@RequestBody Tag tag) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        DataSource dataSource = context.getBean("dataSource", DataSource.class);
        TagJDBCTemplate tagJDBCTemplate = new TagJDBCTemplate();
        tagJDBCTemplate.setDataSource(dataSource);
        tagJDBCTemplate.create(tag);
    }

    @GetMapping("/getAllTags")
    public StringBuilder getAllTags() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        DataSource dataSource = context.getBean("dataSource", DataSource.class);
        TagJDBCTemplate tagJDBCTemplate = new TagJDBCTemplate();
        tagJDBCTemplate.setDataSource(dataSource);
        List<Tag> result = tagJDBCTemplate.listItems();
        StringBuilder list = new StringBuilder();
        for (Tag tag : result) {
            list.append(tag.getName()).append("||");
        }
        return list;
    }
}
