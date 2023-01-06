package com.epam.spm.model;

import java.util.List;

public interface CertificateDAO extends EntityDAO<GiftCertificate> {
    boolean editById(int id, GiftCertificate certificate);

    List<GiftCertificate> listItemsDESC();

    GiftCertificate getEntityByDescription(String description);

    GiftCertificate getEntityByTagName(String tagName);
    List<GiftCertificate> listItemsDateASC();
    List<GiftCertificate> listItemsDateDESC();




}
