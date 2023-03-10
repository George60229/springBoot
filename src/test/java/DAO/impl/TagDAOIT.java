package DAO.impl;

import com.epam.esm.dao.TagDAO;
import com.epam.esm.dao.impl.TagDAOImpl;
import com.epam.esm.model.Tag;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Optional;


public class TagDAOIT extends ConfigFotTest{


    TagDAO tagDAO = new TagDAOImpl(dataSourceH2);


    @Test
    public void createAndDelete() {
        Tag tag = new Tag();
        tag.setName("first");
        tagDAO.create(tag);
        Tag tagResult = tagDAO.getByName("first").get(0);
        Assertions.assertEquals(tag.getName(), tagResult.getName());
        Assertions.assertTrue(tagDAO.deleteById(tagResult.getId()));

    }

    @Test
    public void createAndGetAll() {
        int tagSize=tagDAO.getAllTags().size();
        Tag tag = new Tag();
        tag.setName("firstRT");

        for (int i = 0; i < 10; i++) {
            tagDAO.create(tag);
        }

        Assertions.assertEquals(tagSize+10, tagDAO.getAllTags().size());


    }
    @Test
    public void getById() {
        Tag tag = new Tag();
        tag.setName("firstID");
        Tag expected=tagDAO.create(tag);
        Optional<Tag> ex=tagDAO.getById(expected.getId());
        Assertions.assertFalse(ex.isEmpty());
        Tag result=ex.get();
        Assertions.assertEquals(result.getName(),expected.getName());
        Assertions.assertEquals(result.getId(),expected.getId());
        Assertions.assertTrue(tagDAO.deleteById(expected.getId()));


    }

}
