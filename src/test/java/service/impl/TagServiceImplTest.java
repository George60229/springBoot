package service.impl;

import com.epam.spm.dao.TagDAO;
import com.epam.spm.dao.impl.TagDAOImpl;
import com.epam.spm.dto.response.TagResponseDTO;
import com.epam.spm.model.GiftCertificate;
import com.epam.spm.model.Tag;
import com.epam.spm.service.TagService;
import com.epam.spm.service.impl.TagServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TagServiceImplTest {
    @Mock
    TagDAO tagDAO;
    @InjectMocks
    TagService service=new TagServiceImpl(tagDAO);

    @Test
    public void findByTagName() {










        List<Tag> tags=new ArrayList<>();

        when(tagDAO.getAllTags()).thenReturn(tags);

        service.getAllTags();


    }

}
