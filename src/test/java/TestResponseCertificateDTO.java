
import com.epam.spm.dto.ResponseCertificateDTO;

import com.epam.spm.converter.CertificateConverter;
import com.epam.spm.converter.impl.CertificateConverterImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


import static org.junit.Assert.*;

public class TestResponseCertificateDTO {
    DataSource dataSource;
    ClassPathXmlApplicationContext context;

    @Before
    public void before() {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
        dataSource = context.getBean("dataSource", DataSource.class);
    }

    @Test
    public void testCertificatesToDTO() {

        ResponseCertificateDTO expectedResponseCertificateDTO = new ResponseCertificateDTO();
        expectedResponseCertificateDTO.setCertificateId(1);
        expectedResponseCertificateDTO.setName("test");
        expectedResponseCertificateDTO.setPrice(BigDecimal.valueOf(100));
        expectedResponseCertificateDTO.setDescription("nothing");
        expectedResponseCertificateDTO.setDuration(10);

        com.epam.spm.model.GiftCertificate certificate = new com.epam.spm.model.GiftCertificate();
        certificate.setId(1);
        certificate.setName("test");
        certificate.setPrice(BigDecimal.valueOf(100));
        certificate.setDescription("nothing");
        certificate.setDuration(10);
        List<com.epam.spm.model.GiftCertificate> certificates = new ArrayList<>();
        certificates.add(certificate);
        CertificateConverter service = new CertificateConverterImpl();
        List<ResponseCertificateDTO> resDTO = service.convertToDTO(certificates);

        ResponseCertificateDTO res = resDTO.get(0);

        assertEquals(res.getCertificateId(), expectedResponseCertificateDTO.getCertificateId());
        assertEquals(res.getDescription(), expectedResponseCertificateDTO.getDescription());
        assertEquals(res.getDuration(), expectedResponseCertificateDTO.getDuration());
        assertEquals(res.getName(), expectedResponseCertificateDTO.getName());
        assertEquals(res.getPrice(), expectedResponseCertificateDTO.getPrice());


    }

    @Test
    public void testGetSet() {
        com.epam.spm.model.GiftCertificate certificate = new com.epam.spm.model.GiftCertificate();
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
