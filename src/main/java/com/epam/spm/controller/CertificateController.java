package com.epam.spm.controller;


import com.epam.spm.dto.CertificateDTO;
import com.epam.spm.dto.CreateCertificateDTO;

import com.epam.spm.dao.impl.CertificateDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class CertificateController {

    @Autowired
    private CertificateDAOImpl certificateDAO;



    @GetMapping("/getCertificate")
    public List<CertificateDTO> getCertificate(@RequestParam String name) {

        return certificateDAO.getEntityByName(name);
    }

    @PostMapping("/addCertificate")
    @ResponseStatus(HttpStatus.CREATED)//responseDTO todo
    public CreateCertificateDTO addCertificate(@RequestBody CreateCertificateDTO giftCertificate) {
        return certificateDAO.create(giftCertificate);
    }

    @GetMapping("/getAllCertificates")
    public List<CertificateDTO> getAllCertificate() {
        return certificateDAO.listItems();
    }

    @DeleteMapping("/certificate")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCertificateById(@RequestParam Integer id) {
        certificateDAO.deleteById(id);


    }

    @PatchMapping("/editCertificate")
    @ResponseStatus(HttpStatus.OK)
    public CreateCertificateDTO editCertificateById(@RequestParam Integer id, @RequestBody CreateCertificateDTO certificate) {
        return certificateDAO.editById(id, certificate);
    }

    @GetMapping("/getAllCertificatesDESC")
    public List<CertificateDTO> getAllCertificateDESC() {
        return certificateDAO.listItemsDESC();

    }

    @GetMapping("/findByTagName")
    public List<CertificateDTO> getTagNyName(@RequestParam String tagName) {
        return certificateDAO.findByTagName(tagName);
    }

    @GetMapping("/sortCertificatesByDateDesc")
    public List<CertificateDTO> sortCertificatesByDateDesc() {
        return certificateDAO.listItemsDateDESC();
    }

    @GetMapping("/sortCertificatesByDateAsc")
    public List<CertificateDTO> sortCertificatesByDateAsc() {
        return certificateDAO.listItemsDateASC();
    }

    @GetMapping("/getCertificateByDescription")
    public List<CertificateDTO> sortCertificatesByDateAsc(@RequestParam String description) {
        return certificateDAO.getEntityByDescription(description);
    }

}
