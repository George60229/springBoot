package com.epam.spm.controller;


import com.epam.spm.dao.CertificateDAO;
import com.epam.spm.dto.ResponseCertificateDTO;
import com.epam.spm.dto.RequestCertificateDTO;

import com.epam.spm.exception.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class CertificateController {

    @Autowired
    private CertificateDAO certificateDAO;


    @GetMapping("/getCertificate")
    public List<ResponseCertificateDTO> getCertificate(@RequestParam String name) {

        return certificateDAO.getEntityByName(name);
    }

    @PostMapping("/addCertificate")
    @ResponseStatus(HttpStatus.CREATED)//responseDTO todo
    public ResponseCertificateDTO addCertificate(@RequestBody RequestCertificateDTO giftCertificate) {
        return certificateDAO.createCertificate(giftCertificate);
    }

    @GetMapping("/getAllCertificates")
    public List<ResponseCertificateDTO> getAllCertificate() {
        return certificateDAO.listItems();
    }

    @DeleteMapping("/certificate")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCertificateById(@RequestParam Integer id) {
        certificateDAO.deleteById(id);


    }

    @PatchMapping("/editCertificate")
    @ResponseStatus(HttpStatus.OK)
    public ResponseCertificateDTO editCertificateById(@RequestParam Integer id, @RequestBody RequestCertificateDTO certificate) {
        return certificateDAO.editById(id, certificate);
    }

    @GetMapping("/getAllCertificatesDESC")
    public List<ResponseCertificateDTO> getAllCertificateDESC() {
        return certificateDAO.listItemsDESC();

    }

    @GetMapping("/findByTagName")
    public List<ResponseCertificateDTO> getTagNyName(@RequestParam String tagName) {
        return certificateDAO.findByTagName(tagName);
    }

    @GetMapping("/sortCertificatesByDateDesc")
    public List<ResponseCertificateDTO> sortCertificatesByDateDesc() {
        return certificateDAO.listItemsDateDESC();
    }

    @GetMapping("/sortCertificatesByDateAsc")
    public List<ResponseCertificateDTO> sortCertificatesByDateAsc() {
        return certificateDAO.listItemsDateASC();
    }

    @GetMapping("/getCertificateByDescription")
    public List<ResponseCertificateDTO> sortCertificatesByDateAsc(@RequestParam String description) {
        return certificateDAO.getEntityByDescription(description);
    }

}
