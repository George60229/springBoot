package com.epam.spm.service;

import com.epam.spm.dto.RequestCertificateDTO;
import com.epam.spm.dto.ResponseCertificateDTO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface CertificateService {
    /**
     * get entity by name
     *
     * @param name just name
     * @return list of certificates.
     */
    List<ResponseCertificateDTO> getCertificateByName(String name);

    /**
     * get all entity
     *
     * @return list of certificates.
     */
    List<ResponseCertificateDTO> listCertificates();

    /**
     * delete entity by id
     *
     * @param id just id
     */

    void deleteCertificateById(Integer id);

    /**
     * delete entity by name
     *
     * @param name just name
     * @return result this operation
     */

    boolean deleteCertificateByName(String name);

    List<ResponseCertificateDTO> findByTagName(String tagName);

    ResponseCertificateDTO getCertificateById(@RequestParam int id);
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

    ResponseCertificateDTO editById(int id, RequestCertificateDTO certificate);
    ResponseCertificateDTO createCertificate(@RequestBody RequestCertificateDTO certificateDTO);



}


