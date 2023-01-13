package com.epam.spm.controller;


import com.epam.spm.dto.ResponseCertificateDTO;
import com.epam.spm.dto.RequestCertificateDTO;

import com.epam.spm.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class CertificateController {

    @Autowired
    private CertificateService certificateService;


    @GetMapping("/getCertificate")
    public List<ResponseCertificateDTO> getCertificate(@RequestParam String name) {

        return certificateService.getCertificateByName(name);
    }

    @PostMapping("/addCertificate")
    @ResponseStatus(HttpStatus.CREATED)//responseDTO todo
    public ResponseCertificateDTO addCertificate(@RequestBody RequestCertificateDTO giftCertificate) {
        return certificateService.createCertificate(giftCertificate);
    }

    @GetMapping("/getAllCertificates")
    public List<ResponseCertificateDTO> getAllCertificate() {
        return certificateService.listCertificates();
    }

    @DeleteMapping("/certificate")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCertificateById(@RequestParam Integer id) {
       certificateService.deleteCertificateById(id);


    }

    @PatchMapping("/editCertificate")
    @ResponseStatus(HttpStatus.OK)
    public ResponseCertificateDTO editCertificateById(@RequestParam Integer id, @RequestBody RequestCertificateDTO certificate) {
        return certificateService.editById(id,certificate);
    }

    @GetMapping("/getAllCertificatesDESC")
    public List<ResponseCertificateDTO> getAllCertificateDESC() {
        return certificateService.listItemsDESC();

    }

    @GetMapping("/findByTagName")
    public List<ResponseCertificateDTO> getTagNyName(@RequestParam String tagName) {
        return certificateService.findByTagName(tagName);
    }

    @GetMapping("/sortCertificatesByDateDesc")
    public List<ResponseCertificateDTO> sortCertificatesByDateDesc() {
        return certificateService.listItemsDateDESC();
    }

    @GetMapping("/sortCertificatesByDateAsc")
    public List<ResponseCertificateDTO> sortCertificatesByDateAsc() {
        return certificateService.listItemsDateASC();
    }

    @GetMapping("/getCertificateByDescription")
    public List<ResponseCertificateDTO> sortCertificatesByDateAsc(@RequestParam String description) {
        return certificateService.getEntityByDescription(description);
    }

}
