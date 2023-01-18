import com.epam.spm.dto.response.ResponseCertificateDTO;
import com.epam.spm.converter.CertificateConverter;
import com.epam.spm.model.GiftCertificate;
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
    CertificateConverter serviceMockito;

    @Test
    public void testConvertToDTO() {
        MockitoAnnotations.openMocks(this);
        ResponseCertificateDTO expectedResponseCertificateDTO = new ResponseCertificateDTO();
        expectedResponseCertificateDTO.setCertificateId(1);
        expectedResponseCertificateDTO.setName("test");
        expectedResponseCertificateDTO.setPrice(BigDecimal.valueOf(100));
        expectedResponseCertificateDTO.setDescription("nothing");
        expectedResponseCertificateDTO.setDuration(10);
        GiftCertificate certificate = new GiftCertificate();
        certificate.setId(1);
        certificate.setName("test");
        certificate.setPrice(BigDecimal.valueOf(100));
        certificate.setDescription("nothing");
        certificate.setDuration(10);
        List<ResponseCertificateDTO> responseCertificateDTOList = new ArrayList<>();
        responseCertificateDTOList.add(expectedResponseCertificateDTO);
        List<GiftCertificate> certificates = new ArrayList<>();
        certificates.add(certificate);
        Mockito.doReturn(responseCertificateDTOList).when(serviceMockito).convertListToDTO(certificates);
        assertEquals(responseCertificateDTOList, serviceMockito.convertListToDTO(certificates));
    }

}