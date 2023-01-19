package service.impl;

import com.epam.spm.converter.TagConverter;
import com.epam.spm.dao.CertificateDAO;
import com.epam.spm.dao.TagDAO;
import com.epam.spm.dao.impl.CertificateDAOImpl;
import com.epam.spm.dao.impl.TagDAOImpl;
import com.epam.spm.dto.request.TagRequestDTO;
import com.epam.spm.dto.response.ResponseCertificateDTO;
import com.epam.spm.dto.response.TagResponseDTO;
import com.epam.spm.model.GiftCertificate;
import com.epam.spm.model.Tag;
import com.epam.spm.service.TagService;
import com.epam.spm.service.impl.TagServiceImpl;
import org.checkerframework.checker.units.qual.A;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

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
        when(mock.getById(5)).thenReturn(tag);
        tagService.setAll(mock, new TagConverter());
        Assertions.assertEquals(tagService.getById(5).getName(),tag.getName());
        Assertions.assertTrue(tagService.deleteById(5));
        Assertions.assertEquals(expectedTags.size(), tagService.getAllTags().size());
        Assertions.assertEquals(tags.get(0).getName(), tagService.getByName("Hello").get(0).getName());

    }


}
