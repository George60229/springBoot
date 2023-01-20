package service.impl;

import com.epam.esm.converter.TagConverter;
import com.epam.esm.dao.TagDAO;
import com.epam.esm.dao.impl.TagDAOImpl;
import com.epam.esm.dto.request.TagRequestDTO;
import com.epam.esm.model.Tag;
import com.epam.esm.service.TagService;
import com.epam.esm.service.impl.TagServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TagServiceImplTest {


    TagService tagService = new TagServiceImpl();


    @Test
    public void findByTagName() {
        List<Tag> tags = new ArrayList<>();
        List<TagRequestDTO> expectedTags = new ArrayList<>();
        TagRequestDTO requestDTO=new TagRequestDTO();
        expectedTags.add(requestDTO);
        requestDTO.setName("Hello");
        Tag tag = new Tag();
        tag.setId(5);
        tag.setName("Hello");
        tags.add(tag);
        TagDAO mock = org.mockito.Mockito.mock(TagDAOImpl.class);
        when(mock.getByName("Hello")).thenReturn(tags);
        when(mock.getAllTags()).thenReturn(tags);
        when(mock.deleteById(5)).thenReturn(true);
        when(mock.getById(5)).thenReturn(Optional.of(tag));
        tagService.setAll(mock, new TagConverter());
        Assertions.assertEquals(tagService.getById(5).getName(),tag.getName());
        Assertions.assertTrue(tagService.deleteById(5));
        Assertions.assertEquals(expectedTags.size(), tagService.getAllTags().size());
        Assertions.assertEquals(tags.get(0).getName(), tagService.getByName("Hello").get(0).getName());

    }


}
