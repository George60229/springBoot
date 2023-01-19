package com.epam.spm.controller;


import com.epam.spm.dto.request.CertificateFindByDTO;
import com.epam.spm.dto.response.ResponseCertificateDTO;
import com.epam.spm.dto.request.CertificateRequestDTO;
import com.epam.spm.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class CertificateController {

    @Autowired
    private CertificateService certificateService;

    @GetMapping("/getCertificateById/{id}")
    public ResponseCertificateDTO getCertificateById(@PathVariable(value = "id") int id) {
        return certificateService.getCertificateById(id);
    }

    @PostMapping("/addCertificate")
    public ResponseCertificateDTO addCertificate(@RequestBody CertificateRequestDTO giftCertificate) {
        return certificateService.createCertificate(giftCertificate);
    }

    @GetMapping("/getAllCertificates")
    public List<ResponseCertificateDTO> getAllCertificate(@RequestBody CertificateFindByDTO certificateFindByDTO) {
        return certificateService.listCertificates(certificateFindByDTO);
    }

    @DeleteMapping("/certificate/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCertificateById(@PathVariable(value = "id") Integer id) {
        certificateService.deleteCertificateById(id);
    }

    @PatchMapping("/editCertificate/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseCertificateDTO editCertificateById(@PathVariable(value = "id") Integer id, @RequestBody CertificateRequestDTO certificate) {
        return certificateService.editById(id, certificate);
    }

    @GetMapping("/findByTagName/{tagName}")
    public List<ResponseCertificateDTO> getByTagName(@PathVariable(value = "tagName") String tagName) {
        return certificateService.findByTagName(tagName);
    }

}
