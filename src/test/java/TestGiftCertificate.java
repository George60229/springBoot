
import com.epam.spm.dto.CertificateDTO;
import com.epam.spm.model.GiftCertificate;

import com.epam.spm.converter.CertificateService;
import com.epam.spm.converter.CertificateServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


import static org.junit.Assert.*;

public class TestGiftCertificate {
    DataSource dataSource;
    ClassPathXmlApplicationContext context;

    @Before
    public void before() {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
        dataSource = context.getBean("dataSource", DataSource.class);
    }

    @Test
    public void testCertificatesToDTO() {

        CertificateDTO expectedCertificateDTO = new CertificateDTO();
        expectedCertificateDTO.setCertificateId(1);
        expectedCertificateDTO.setName("test");
        expectedCertificateDTO.setPrice(BigDecimal.valueOf(100));
        expectedCertificateDTO.setDescription("nothing");
        expectedCertificateDTO.setDuration(10);

        GiftCertificate certificate = new GiftCertificate();
        certificate.setId(1);
        certificate.setName("test");
        certificate.setPrice(BigDecimal.valueOf(100));
        certificate.setDescription("nothing");
        certificate.setDuration(10);
        List<GiftCertificate> certificates = new ArrayList<>();
        certificates.add(certificate);
        CertificateService service = new CertificateServiceImpl();
        List<CertificateDTO> resDTO = service.convertToDTO(certificates);

        CertificateDTO res = resDTO.get(0);

        assertEquals(res.getCertificateId(), expectedCertificateDTO.getCertificateId());
        assertEquals(res.getDescription(), expectedCertificateDTO.getDescription());
        assertEquals(res.getDuration(), expectedCertificateDTO.getDuration());
        assertEquals(res.getName(), expectedCertificateDTO.getName());
        assertEquals(res.getPrice(), expectedCertificateDTO.getPrice());


    }

    @Test
    public void testGetSet() {
        GiftCertificate certificate = new GiftCertificate();
        certificate.setName("Tested");
        certificate.setDuration(1000);
        certificate.setDescription("It is amazing");
        certificate.setPrice(BigDecimal.valueOf(150));

        assertEquals("Tested", certificate.getName());
        assertEquals(1000, certificate.getDuration());
        assertEquals("It is amazing", certificate.getDescription());
        assertEquals(BigDecimal.valueOf(150), certificate.getPrice());


    }
}
