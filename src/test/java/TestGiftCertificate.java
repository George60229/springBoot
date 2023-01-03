
import com.epam.spm.Gift_certificate;
import com.epam.spm.JDBC.CertificatesJDBCTemplate;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;

import java.sql.Date;

import static org.junit.Assert.assertEquals;

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

        certificatesJDBCTemplate.create("test_from_hear", 200);
        Gift_certificate certificate = certificatesJDBCTemplate.getEntityByName("test_from_hear");

        assertEquals("test_from_hear", certificate.getName());
        assertEquals(200, certificate.getPrice());
        certificatesJDBCTemplate.deleteById(certificate.getId());
    }

    @Test
    public void testGetSet() {
        Gift_certificate certificate = new Gift_certificate();
        certificate.setName("Tested");
        certificate.setDuration(1000);
        certificate.setDescription("It is amazing");
        certificate.setCreate_date(new Date(10_000_000));
        certificate.setPrice(150);

        assertEquals("Tested", certificate.getName());
        assertEquals(1000, certificate.getDuration());
        assertEquals("It is amazing", certificate.getDescription());
        assertEquals(new Date(10_000_000), certificate.getCreate_date());
        assertEquals(150, certificate.getPrice());


    }
}
