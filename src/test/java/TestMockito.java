import com.epam.spm.dto.CertificateDTO;
import com.epam.spm.model.GiftCertificate;
import com.epam.spm.converter.CertificateService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;



public class TestMockito {
    @BeforeEach
    public void init() {

    }

    @Mock
    CertificateService serviceMockito;

    @Test
    public void testConvertToDTO() {
        MockitoAnnotations.openMocks(this);
        CertificateDTO expectedCertificateDTO = new CertificateDTO();
        expectedCertificateDTO.setCertificateId(1);
        expectedCertificateDTO.setName("test");
        expectedCertificateDTO.setPrice(BigDecimal.valueOf(100));
        expectedCertificateDTO.setDescription("nothing");
        expectedCertificateDTO.setDuration(10);

        GiftCertificate certificate=new GiftCertificate();
        certificate.setId(1);
        certificate.setName("test");
        certificate.setPrice(BigDecimal.valueOf(100));
        certificate.setDescription("nothing");
        certificate.setDuration(10);
        List<CertificateDTO>certificateDTOList=new ArrayList<>();
        certificateDTOList.add(expectedCertificateDTO);
        List<GiftCertificate> certificates = new ArrayList<>();
        certificates.add(certificate);


        Mockito.doReturn(certificateDTOList).when(serviceMockito).convertToDTO(certificates);

        assertEquals(certificateDTOList, serviceMockito.convertToDTO(certificates));
    }

}