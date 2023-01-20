package service.impl;

import com.epam.esm.converter.CertificateConverter;
import com.epam.esm.dao.CertificateDAO;
import com.epam.esm.dto.request.CertificateFindByDTO;
import com.epam.esm.dto.request.CertificateRequestDTO;
import com.epam.esm.dto.response.ResponseCertificateDTO;
import com.epam.esm.exception.AppNotFoundException;
import com.epam.esm.exception.BadRequestException;
import com.epam.esm.exception.ErrorCode;
import com.epam.esm.model.GiftCertificate;
import com.epam.esm.service.CertificateService;
import com.epam.esm.service.impl.CertificateServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CertificateServiceImplTest {


    CertificateService certificateService = new CertificateServiceImpl();


    @Test(expected = AppNotFoundException.class)
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

        Assertions.assertEquals(certificateService.editById(5,requestDTO).getCertificateId(),5);
        Assertions.assertEquals(certificateService.getCertificateById(5).getName(), giftCertificate.getName());
        Assertions.assertTrue(certificateService.deleteCertificateById(5));
        Assertions.assertEquals(expectedCertificates.size(), certificateService.listCertificates(
                certificateFindByDTO).size());
        Assertions.assertEquals(giftCertificates.get(0).getName(),
                certificateService.listCertificates(certificateFindByDTO).get(0).getName());
        Assertions.assertEquals(certificateService.findByTagName("test").get(0).getName(),
                giftCertificates.get(0).getName());
        Assertions.assertEquals(certificateService.editById(5,requestDTO).getName(), giftCertificate.getName());
        Assertions.assertThrows(AppNotFoundException.class, (Executable) certificateService.getCertificateById(100));
    }


}

