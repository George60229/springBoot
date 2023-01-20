package com.epam.esm.service;

import com.epam.esm.converter.CertificateConverter;
import com.epam.esm.dao.CertificateDAO;
import com.epam.esm.dto.request.CertificateFindByDTO;
import com.epam.esm.dto.request.CertificateRequestDTO;
import com.epam.esm.dto.response.ResponseCertificateDTO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface CertificateService {

    void setAll(CertificateDAO certificateDAO, CertificateConverter tagConverter);


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

    boolean deleteCertificateById(Integer id);

    /**
     * delete entity by name
     *
     * @param tagName just name
     * @return result this operation
     */


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


