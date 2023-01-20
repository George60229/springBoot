package DAO.impl;

import com.epam.esm.dao.CertificateDAO;
import com.epam.esm.dao.impl.CertificateDAOImpl;
import com.epam.esm.dto.request.CertificateFindByDTO;
import com.epam.esm.exception.BadRequestException;
import com.epam.esm.model.GiftCertificate;
import com.epam.esm.utils.FindParameter;
import com.epam.esm.utils.SortParameter;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.Optional;

public class CertificateDAOITTest extends ConfigFotTest {

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
        CertificateFindByDTO certificateFindByDTO = new CertificateFindByDTO();
        certificateFindByDTO.setValue("firstRT");
        certificateFindByDTO.setFindParameter(FindParameter.NAME);
        int res = certificateDAO.listItems(certificateFindByDTO).size();
        for (int i = res; i < res + 10; i++) {
            certificateDAO.createCertificate(giftCertificate);
        }

        Assertions.assertEquals(certificateDAO.listItems(certificateFindByDTO).size(), res + 10);
        certificateFindByDTO.setFindParameter(FindParameter.DESCRIPTION);
        certificateFindByDTO.setSortParameter(SortParameter.DATE);
        Assertions.assertEquals(certificateDAO.listItems(certificateFindByDTO).size(), 0);
        Assertions.assertTrue(certificateDAO.deleteById(giftCertificate.getId()));

    }

    @Test
    public void findByTagName() {
        GiftCertificate giftCertificate = new GiftCertificate();
        giftCertificate.setName("firstRTUYU");
        giftCertificate.addTag("WOW");
        certificateDAO.createCertificate(giftCertificate);
        GiftCertificate res = certificateDAO.findByTagName("WOW").get(0);

        Assertions.assertEquals(giftCertificate.getName(), res.getName());
        Assertions.assertEquals(giftCertificate.getTags().get(0), "WOW");
        Assertions.assertTrue(certificateDAO.deleteById(res.getId()));

    }

    @Test
    public void update() {
        GiftCertificate giftCertificate = new GiftCertificate();
        giftCertificate.setName("firstRTUYU");
        giftCertificate.addTag("WOW");
        GiftCertificate res = certificateDAO.createCertificate(giftCertificate);
        res.setName("second");
        res.setDescription("wowo");
        res.setDuration(100);
        res.addTag(null);
        certificateDAO.update(res);
        res.addTag("GO");

        GiftCertificate result = certificateDAO.findByTagName("WOW").get(0);

        Assertions.assertEquals(giftCertificate.getName(), result.getName());
        Assertions.assertEquals(giftCertificate.getTags().get(0), "WOW");
        Assertions.assertTrue(certificateDAO.deleteById(res.getId()));
        Assertions.assertEquals(res.getName(), "second");
        Assertions.assertEquals(res.getDescription(), "wowo");
        Assertions.assertEquals(res.getDuration(), 100);
        CertificateFindByDTO certificateFindByDTO = new CertificateFindByDTO();
        certificateFindByDTO.setFindParameter(FindParameter.DESCRIPTION);
        certificateFindByDTO.setValue("ALALLALA");
        certificateFindByDTO.setSortParameter(SortParameter.DATE);
        Assertions.assertEquals(certificateDAO.findByTagName("ffgfgfg"), new ArrayList<>());
        Assertions.assertEquals(certificateDAO.listItems(certificateFindByDTO), new ArrayList<>());

    }

    @Test(expected = BadRequestException.class)
    public void anotherTest() {
        CertificateFindByDTO certificateFindByDTO=new CertificateFindByDTO();
        certificateFindByDTO.setSortParameter(SortParameter.NAME);
        certificateDAO.listItems(certificateFindByDTO);
        Assertions.assertEquals(certificateDAO.getCertificateById(1000000000),Optional.empty());
        certificateFindByDTO.setFindParameter(FindParameter.NAME);
        certificateDAO.listItems(certificateFindByDTO);
    }
    @Test
    public void lastOne(){

        GiftCertificate giftCertificate = new GiftCertificate();

        giftCertificate.setName("firstRTUYU");
        giftCertificate.addTag("go");
        certificateDAO.createCertificate(giftCertificate);


    }

}
