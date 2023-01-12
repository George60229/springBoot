import com.epam.spm.dto.TagDTO;
import com.epam.spm.model.Tag;
import com.epam.spm.converter.TagService;
import com.epam.spm.converter.TagServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


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

        TagDTO expectedTagDTO = new TagDTO();
        expectedTagDTO.setId(1);
        expectedTagDTO.setName("test");
        Tag tag = new Tag();
        tag.setId(1);
        tag.setName("test");

        List<Tag> tags = new ArrayList<>();
        tags.add(tag);
        TagService service = new TagServiceImpl();
        List<TagDTO> resDTO = service.convert(tags);
        TagDTO res = resDTO.get(0);
        assertEquals(res.getId(), expectedTagDTO.getId());
        assertEquals(res.getName(), expectedTagDTO.getName());


    }


    @Test
    public void testGetSet() {
        Tag tag = new Tag();
        tag.setName("ALALAL");
        tag.setId(1);
        assertEquals("ALALAL", tag.getName());
        assertEquals(1, tag.getId());

    }
}
