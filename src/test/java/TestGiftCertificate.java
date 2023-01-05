
import com.epam.spm.model.GiftCertificate;

import com.epam.spm.dao.CertificatesJDBCTemplate;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;

import java.math.BigDecimal;
import java.sql.Date;

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
    public void testCertificatesToDB() {
        CertificatesJDBCTemplate certificatesJDBCTemplate = new CertificatesJDBCTemplate();
        certificatesJDBCTemplate.setDataSource(dataSource);
        context.close();
        GiftCertificate newCertificate = new GiftCertificate();
        newCertificate.setName("test_from_hear");
        newCertificate.setPrice(BigDecimal.valueOf(200));
        certificatesJDBCTemplate.create(newCertificate);
        GiftCertificate certificate = certificatesJDBCTemplate.getEntityByName("test_from_hear");

        assertEquals("test_from_hear", certificate.getName());
        assertEquals(BigDecimal.valueOf(200), certificate.getPrice());
        assertTrue(certificatesJDBCTemplate.deleteByName("test_from_hear"));
        //todo integration tests

    }

    @Test
    public void testGetSet() {
        GiftCertificate certificate = new GiftCertificate();
        certificate.setName("Tested");
        certificate.setDuration(1000);
        certificate.setDescription("It is amazing");
        certificate.setCreateDate(new Date(10_000_000));
        certificate.setPrice(BigDecimal.valueOf(150));

        assertEquals("Tested", certificate.getName());
        assertEquals(1000, certificate.getDuration());
        assertEquals("It is amazing", certificate.getDescription());
        assertEquals(new Date(10_000_000), certificate.getCreateDate());
        assertEquals(BigDecimal.valueOf(150), certificate.getPrice());


    }
}
