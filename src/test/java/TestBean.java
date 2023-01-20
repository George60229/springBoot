import com.epam.esm.dao.CertificateDAO;
import com.epam.esm.dao.impl.CertificateDAOImpl;
import com.epam.esm.dao.TagDAO;
import com.epam.esm.dao.impl.TagDAOImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;

import static org.junit.Assert.assertNotNull;


public class TestBean {

    ClassPathXmlApplicationContext context;

    @Before
    public void before() {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");

    }

    @Test
    public void testGetBeanFromContext() {

        DataSource dataSource = context.getBean("dataSource", DataSource.class);
        assertNotNull(dataSource);
        CertificateDAO certificateDAO = context.getBean("certificateDAO", CertificateDAOImpl.class);
        assertNotNull(certificateDAO);
        TagDAO tagDAO = context.getBean("tagDAO", TagDAOImpl.class);
        assertNotNull(tagDAO);

    }
}
