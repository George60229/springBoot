package com.epam.spm.dao;

import com.epam.spm.model.GiftCertificate;
import com.epam.spm.utils.SortParameter;
import com.epam.spm.utils.SortWay;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

public interface CertificateDAO extends EntityDAO<GiftCertificate> {

    /**
     * edit entity by id
     *
     *        id of changed entity
     * @param certificate a list of changed fields
     * @return result this operation(successful or not).
     */
    GiftCertificate update( GiftCertificate certificate);

    /**
     * get all entity ordered by desc
     *
     * @return list of certificates.
     */
    List<GiftCertificate> listItems(SortParameter sortParameter, SortWay sortWay);

    /**
     * get all entity by description
     *
     * @return list of certificates.
     */

  List<GiftCertificate> getCertificateByDescription(String description);

    /**
     * get all entity ordered by date asc
     *
     * @return list of certificates.
     */



    List<GiftCertificate> findByTagName(String tagName);

    Optional<GiftCertificate> getCertificateById(@RequestParam int id);

    GiftCertificate createCertificate(GiftCertificate certificateDTO);


}
