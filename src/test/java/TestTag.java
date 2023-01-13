import com.epam.spm.dto.ResponseTagDTO;
import com.epam.spm.model.Tag;
import com.epam.spm.converter.TagConverter;
import com.epam.spm.converter.impl.TagConverterImpl;
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

        ResponseTagDTO expectedResponseTagDTO = new ResponseTagDTO();
        expectedResponseTagDTO.setId(1);
        expectedResponseTagDTO.setName("test");
        Tag tag = new Tag();
        tag.setId(1);
        tag.setName("test");

        List<Tag> tags = new ArrayList<>();
        tags.add(tag);
        TagConverter service = new TagConverterImpl();
        List<ResponseTagDTO> resDTO = service.convert(tags);
        ResponseTagDTO res = resDTO.get(0);
        assertEquals(res.getId(), expectedResponseTagDTO.getId());
        assertEquals(res.getName(), expectedResponseTagDTO.getName());


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
