import com.epam.spm.dao.TagJDBCTemplate;
import com.epam.spm.model.Tag;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestTag {
    DataSource dataSource;
    ClassPathXmlApplicationContext context;

    @Before
    public void before() {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
        dataSource = context.getBean("dataSource", DataSource.class);
    }

    @Test
    public void testTagToDB() {
        TagJDBCTemplate tagJDBCTemplate = new TagJDBCTemplate();
        tagJDBCTemplate.setDataSource(dataSource);
        context.close();
        Tag newTag=new Tag();

        newTag.setName("tested_tag");
        tagJDBCTemplate.create(newTag);

        Tag tag = tagJDBCTemplate.getEntityByName("tested_tag");
        assertEquals("tested_tag", tag.getName());
        assertTrue(tagJDBCTemplate.deleteByName("tested_tag"));

    }

    @Test
    public void testGetSet() {
        Tag tag = new Tag();
        tag.setName("ALALAL");
        assertEquals("ALALAL", tag.getName());

    }
}
