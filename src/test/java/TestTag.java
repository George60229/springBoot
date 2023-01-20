import com.epam.esm.dto.response.TagResponseDTO;
import com.epam.esm.model.Tag;
import com.epam.esm.converter.TagConverter;
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

        TagResponseDTO expectedTagResponseDTO = new TagResponseDTO();
        expectedTagResponseDTO.setId(1);
        expectedTagResponseDTO.setName("test");
        Tag tag = new Tag();
        tag.setId(1);
        tag.setName("test");

        List<Tag> tags = new ArrayList<>();
        tags.add(tag);
        TagConverter service = new TagConverter();
        List<TagResponseDTO> resDTO = service.convert(tags);
        TagResponseDTO res = resDTO.get(0);
        assertEquals(res.getId(), expectedTagResponseDTO.getId());
        assertEquals(res.getName(), expectedTagResponseDTO.getName());


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
