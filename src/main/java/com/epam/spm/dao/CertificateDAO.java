package com.epam.spm.dao;

import com.epam.spm.dto.ResponseCertificateDTO;
import com.epam.spm.dto.RequestCertificateDTO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface CertificateDAO extends EntityDAO<ResponseCertificateDTO> {

    /**
     * edit entity by id
     *
     * @param id          id of changed entity
     * @param certificate a list of changed fields
     * @return result this operation(successful or not).
     */
    ResponseCertificateDTO editById(int id, RequestCertificateDTO certificate);

    /**
     * get all entity ordered by desc
     *
     * @return list of certificates.
     */
    List<ResponseCertificateDTO> listItemsDESC();

    /**
     * get all entity by description
     *
     * @return list of certificates.
     */

    List<ResponseCertificateDTO> getEntityByDescription(String description);

    /**
     * get all entity ordered by date asc
     *
     * @return list of certificates.
     */
    List<ResponseCertificateDTO> listItemsDateASC();

    /**
     * get all entity ordered by date desc
     *
     * @return list of certificates.
     */
    List<ResponseCertificateDTO> listItemsDateDESC();

    /**
     * get all certificates with this tag
     *
     * @param tagName name of founded tag
     * @return list of certificates.
     */

    List<ResponseCertificateDTO> findByTagName(String tagName);

    ResponseCertificateDTO getCertificateById(@RequestParam int id);

    ResponseCertificateDTO createCertificate(@RequestBody RequestCertificateDTO certificateDTO);


}
