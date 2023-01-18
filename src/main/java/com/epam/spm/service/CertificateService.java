package com.epam.spm.service;

import com.epam.spm.dto.request.CertificateFindByDTO;
import com.epam.spm.dto.request.CertificateRequestDTO;
import com.epam.spm.dto.response.ResponseCertificateDTO;
import com.epam.spm.utils.SortParameter;
import com.epam.spm.utils.SortWay;
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


    /**
     * get all entity
     *
     * @return list of certificates.
     */


    List<ResponseCertificateDTO> listCertificates(CertificateFindByDTO certificateFindByDTO);

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


    /**
     * get all entity by description
     *
     * @return list of certificates.
     */



    /**
     * get all entity ordered by date asc
     *
     * @return list of certificates.
     */


    /**
     * get all entity ordered by date desc
     *
     * @return list of certificates.
     */

    ResponseCertificateDTO editById(int id, CertificateRequestDTO certificate);
    ResponseCertificateDTO createCertificate(@RequestBody CertificateRequestDTO certificateDTO);



}


