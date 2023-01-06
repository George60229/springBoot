package com.epam.spm.dao;

import com.epam.spm.model.CertificateDAO;
import com.epam.spm.model.GiftCertificate;
import com.epam.spm.mapper.GiftMapper;

import java.util.ArrayList;
import java.util.List;

public class CertificatesJDBCTemplate extends AbstractJDBCTemplate implements CertificateDAO {


    final private String FIND_ALL_QUEUE = "SELECT certificates.certificate_id,create_date, description, duration, last_update_date, name, price, tag_id\n" +
            "FROM certificates\n" +
            "         FULL JOIN certificates_to_tages on certificates.certificate_id = certificates_to_tages.certificate_id\n";


    public GiftCertificate create(GiftCertificate certificate) {
        String sqlQuery = "insert into certificates (name, price,create_date,description,duration,last_update_date) values (?, ?,?,?,?,?)";
        jdbcTemplateObject.update(sqlQuery, certificate.getName(), certificate.getPrice(), certificate.getCreateDate(), certificate.getDescription(), certificate.getDuration(), certificate.getLastUpdateDate());
        return certificate;

    }

    @Override
    public GiftCertificate getEntityByName(String name) {
        //model to db
        //dto to front
        String SQL = "SELECT certificates.certificate_id,create_date, description, duration, last_update_date, name, price, tag_id\n" +
                "FROM certificates\n" +
                "         FULL JOIN certificates_to_tages on certificates.certificate_id = certificates_to_tages.certificate_id\n" +
                "where certificates.name='" + name + "'";
        List<GiftCertificate> result = jdbcTemplateObject.query(SQL, new GiftMapper());
        if (result.size()==0){
            throw new IllegalArgumentException("There are no certificates with this name: "+name);
        }
        List<Integer> tags = new ArrayList<>();
        for (GiftCertificate certificate : result) {
            tags.add(certificate.getTags().get(0));
        }
        result.get(0).setTags(tags);
        return result.get(0);

    }

    @Override
    public GiftCertificate getEntityByDescription(String description) {
        //model to db
        //dto to front
        String SQL = "SELECT certificates.certificate_id,create_date, description, duration, last_update_date, name, price, tag_id\n" +
                "FROM certificates\n" +
                "         FULL JOIN certificates_to_tages on certificates.certificate_id = certificates_to_tages.certificate_id\n" +
                "where certificates.name='" + description + "'";
        List<GiftCertificate> result = jdbcTemplateObject.query(SQL, new GiftMapper());
        if (result.size()==0){
            throw new IllegalArgumentException("There are no certificates with this description: "+description);
        }
        List<Integer> tags = new ArrayList<>();
        for (GiftCertificate certificate : result) {
            tags.add(certificate.getTags().get(0));
        }
        result.get(0).setTags(tags);
        return result.get(0);

    }

    @Override
    public GiftCertificate getEntityByTagName(String tagName) {
        return null;
    }

    @Override
    public List<GiftCertificate> listItems() {
        String sqlQueue=FIND_ALL_QUEUE + "order by name ASC ";
        return jdbcTemplateObject.query(sqlQueue, new GiftMapper());

    }

    @Override
    public boolean deleteById(Integer id) {
        String SQL = "delete from certificates where certificate_id=" + id;
        if (jdbcTemplateObject.update(SQL) == 0) {

            throw new IllegalArgumentException("Certificate with this id "+id +"is not deleted:");
        }
        return true;
    }

    @Override
    public boolean deleteByName(String name) {
        String SQL = "delete from certificates where name='" + name + "'";
        if (jdbcTemplateObject.update(SQL) == 0) {
            throw new IllegalArgumentException("Certificate with this name "+name +"is not deleted:");
        }
        return true;
    }

    @Override
    public boolean editById(int id, GiftCertificate certificate) {
        String sqlQueue = "UPDATE certificates" + " SET";

        if (certificate.getCreateDate() != null) {
            sqlQueue += " create_date =" + certificate.getCreateDate() + ",";
        }
        if (certificate.getDescription() != null) {
            sqlQueue += " description =" + certificate.getCreateDate() + ",";
        }
        if (certificate.getDuration() != 0) {
            sqlQueue += " duration=" + certificate.getDuration() + ",";
        }
        if (certificate.getLastUpdateDate() != null) {
            sqlQueue += " last_update_date=" + certificate.getLastUpdateDate() + ",";
        }
        if (certificate.getName() != null) {
            sqlQueue += " name='" + certificate.getName() + "',";
        }
        if (certificate.getPrice() != null) {
            sqlQueue += " price='" + certificate.getName() + "',";
        }
        sqlQueue = sqlQueue.substring(0, sqlQueue.length() - 1);
        if (jdbcTemplateObject.update(sqlQueue) == 0){
            throw new IllegalArgumentException("Field is not edited"+certificate);
        }
    return true;
    }

    @Override
    public List<GiftCertificate> listItemsDESC() {
        String sqlQueue = FIND_ALL_QUEUE + "order by create_date DESC ";
        return jdbcTemplateObject.query(sqlQueue, new GiftMapper());

    }

    @Override
    public List<GiftCertificate> listItemsDateASC() {
        String sqlQueue = FIND_ALL_QUEUE + "order by create_date ASC ";
        return jdbcTemplateObject.query(sqlQueue, new GiftMapper());

    }

    @Override
    public List<GiftCertificate> listItemsDateDESC() {
        String sqlQueue = FIND_ALL_QUEUE + "order by create_date DESC ";
        return jdbcTemplateObject.query(sqlQueue, new GiftMapper());

    }
}
