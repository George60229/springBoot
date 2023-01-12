package com.epam.spm.dao;

import com.epam.spm.dto.CertificateDTO;
import com.epam.spm.dto.CreateCertificateDTO;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface CertificateDAO extends EntityDAO<CertificateDTO> {

    /**
     * edit entity by id
     *
     * @param id          id of changed entity
     * @param certificate a list of changed fields
     * @return result this operation(successful or not).
     */
    CreateCertificateDTO editById(int id, CreateCertificateDTO certificate);

    /**
     * get all entity ordered by desc
     *
     * @return list of certificates.
     */
    List<CertificateDTO> listItemsDESC();

    /**
     * get all entity by description
     *
     * @return list of certificates.
     */

    List<CertificateDTO> getEntityByDescription(String description);

    /**
     * get all entity ordered by date asc
     *
     * @return list of certificates.
     */
    List<CertificateDTO> listItemsDateASC();

    /**
     * get all entity ordered by date desc
     *
     * @return list of certificates.
     */
    List<CertificateDTO> listItemsDateDESC();

    /**
     * get all certificates with this tag
     *
     * @param tagName name of founded tag
     * @return list of certificates.
     */

    List<CertificateDTO> findByTagName(String tagName);

    CertificateDTO getCertificateById(@RequestParam int id);


}
