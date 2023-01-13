package com.epam.spm.dao;

import com.epam.spm.dto.ResponseCertificateDTO;
import com.epam.spm.dto.RequestCertificateDTO;
import com.epam.spm.model.GiftCertificate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface CertificateDAO extends EntityDAO<GiftCertificate> {

    /**
     * edit entity by id
     *
     * @param id          id of changed entity
     * @param certificate a list of changed fields
     * @return result this operation(successful or not).
     */
    RequestCertificateDTO editById(int id, RequestCertificateDTO certificate);

    /**
     * get all entity ordered by desc
     *
     * @return list of certificates.
     */
    List<GiftCertificate> listItemsDESC();

    /**
     * get all entity by description
     *
     * @return list of certificates.
     */

    List<GiftCertificate> getEntityByDescription(String description);

    /**
     * get all entity ordered by date asc
     *
     * @return list of certificates.
     */
    List<GiftCertificate> listItemsDateASC();

    /**
     * get all entity ordered by date desc
     *
     * @return list of certificates.
     */
    List<GiftCertificate> listItemsDateDESC();

    /**
     * get all certificates with this tag
     *
     * @param tagName name of founded tag
     * @return list of certificates.
     */

    List<GiftCertificate> findByTagName(String tagName);

    GiftCertificate getCertificateById(@RequestParam int id);

    RequestCertificateDTO createCertificate(@RequestBody RequestCertificateDTO certificateDTO);


}
