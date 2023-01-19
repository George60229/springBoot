package DAO.impl;

import com.epam.spm.dao.CertificateDAO;
import com.epam.spm.dao.impl.CertificateDAOImpl;
import com.epam.spm.dto.request.CertificateFindByDTO;
import com.epam.spm.dto.response.ResponseCertificateDTO;
import com.epam.spm.model.GiftCertificate;
import com.epam.spm.model.Tag;
import com.epam.spm.utils.FindParameter;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Optional;

public class CertificateDAOImplTest extends ConfigFotTest {

    CertificateDAO certificateDAO = new CertificateDAOImpl(dataSourceH2);


    @Test
    public void createAndGetAll() {
        GiftCertificate certificate = new GiftCertificate();
        certificate.setName("test");
        certificate.setDuration(10);
        certificate.setDescription("Amazing");
        certificate.addTag("test");
        GiftCertificate created = certificateDAO.createCertificate(certificate);
        Optional<GiftCertificate> expected = certificateDAO.getCertificateById(created.getId());
        Assertions.assertTrue(expected.isPresent());
        GiftCertificate result = expected.get();
        Assertions.assertEquals(result.getName(), certificate.getName(), "test");
        Assertions.assertEquals(result.getDuration(), certificate.getDuration(), 10);
        Assertions.assertEquals(result.getDescription(), certificate.getDescription(), "Amazing");
        Assertions.assertTrue(certificateDAO.deleteById(created.getId()));


    }
    @Test
    public void create() {
        GiftCertificate giftCertificate = new GiftCertificate();
        giftCertificate.setName("firstRT");
        for (int i = 0; i < 10; i++) {
            certificateDAO.createCertificate(giftCertificate);
        }
        CertificateFindByDTO certificateFindByDTO=new CertificateFindByDTO();
        certificateFindByDTO.setValue("firstRT");
        certificateFindByDTO.setFindParameter(FindParameter.NAME);
        Assertions.assertEquals(certificateDAO.listItems(certificateFindByDTO).size(),10);
        Assertions.assertTrue(certificateDAO.deleteById(giftCertificate.getId()));

    }

    @Test
    public void findByTagName() {
        GiftCertificate giftCertificate = new GiftCertificate();
        giftCertificate.setName("firstRTUYU");
        giftCertificate.addTag("WOW");
        certificateDAO.createCertificate(giftCertificate);
        GiftCertificate res=certificateDAO.findByTagName("WOW").get(0);

        Assertions.assertEquals(giftCertificate.getName(),res.getName());
        Assertions.assertEquals(giftCertificate.getTags().get(0),"WOW");
        Assertions.assertTrue(certificateDAO.deleteById(res.getId()));

    }
}
