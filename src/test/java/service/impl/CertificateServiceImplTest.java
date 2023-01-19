package service.impl;

import com.epam.spm.converter.CertificateConverter;
import com.epam.spm.converter.TagConverter;
import com.epam.spm.dao.CertificateDAO;
import com.epam.spm.dao.TagDAO;
import com.epam.spm.dao.impl.TagDAOImpl;
import com.epam.spm.dto.request.CertificateFindByDTO;
import com.epam.spm.dto.request.CertificateRequestDTO;
import com.epam.spm.dto.request.TagRequestDTO;
import com.epam.spm.dto.response.ResponseCertificateDTO;
import com.epam.spm.model.GiftCertificate;
import com.epam.spm.model.Tag;
import com.epam.spm.service.CertificateService;
import com.epam.spm.service.TagService;
import com.epam.spm.service.impl.CertificateServiceImpl;
import com.epam.spm.service.impl.TagServiceImpl;
import org.checkerframework.checker.units.qual.A;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CertificateServiceImplTest {


    CertificateService certificateService = new CertificateServiceImpl();


    @Test
    public void findByTagName() {

        ResponseCertificateDTO responseCertificateDTO = new ResponseCertificateDTO();
        responseCertificateDTO.setName("test");
        List<GiftCertificate> giftCertificates = new ArrayList<>();
        List<CertificateRequestDTO> expectedCertificates = new ArrayList<>();
        CertificateRequestDTO requestDTO = new CertificateRequestDTO();
        expectedCertificates.add(requestDTO);
        requestDTO.setName("Hello");
        GiftCertificate giftCertificate = new GiftCertificate();
        giftCertificate.setId(5);
        giftCertificate.setName("Hello");
        giftCertificates.add(giftCertificate);
        CertificateDAO mock = org.mockito.Mockito.mock(CertificateDAO.class);
        CertificateFindByDTO certificateFindByDTO = new CertificateFindByDTO();
        when(mock.listItems(certificateFindByDTO)).thenReturn(giftCertificates);
        when(mock.deleteById(5)).thenReturn(true);
        when(mock.getCertificateById(5)).thenReturn(Optional.of(giftCertificate));
        when(mock.findByTagName("test")).thenReturn(giftCertificates);
        when(mock.update(giftCertificate)).thenReturn(giftCertificate);
        certificateService.setAll(mock, new CertificateConverter());
        Assertions.assertEquals(certificateService.getCertificateById(5).getName(), giftCertificate.getName());
        Assertions.assertTrue(certificateService.deleteCertificateById(5));
        Assertions.assertEquals(expectedCertificates.size(), certificateService.listCertificates(
                certificateFindByDTO).size());
        Assertions.assertEquals(giftCertificates.get(0).getName(),
                certificateService.listCertificates(certificateFindByDTO).get(0).getName());
        Assertions.assertEquals(certificateService.findByTagName("test").get(0).getName(), giftCertificates.get(0).getName());
        Assertions.assertEquals(certificateService.editById(5,requestDTO).getName(), giftCertificate.getName());
    }


}

