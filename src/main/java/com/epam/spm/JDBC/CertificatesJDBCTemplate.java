package com.epam.spm.JDBC;

import com.epam.spm.EntityDAO;
import com.epam.spm.model.GiftCertificate;

import com.epam.spm.mapper.GiftMapper;

import java.util.List;

public class CertificatesJDBCTemplate extends AbstractJDBCTemplate implements EntityDAO<GiftCertificate> {


    public void create(GiftCertificate certificate) {
        String SQL = "insert into certificates (name, price,create_date,description,duration,last_update_date) values (?, ?)";
        jdbcTemplateObject.update(SQL, certificate.getName(), certificate.getPrice(), certificate.getCreate_date(), certificate.getDescription(), certificate.getLast_update_date());


    }

    @Override
    public GiftCertificate getEntityByName(String name) {
        String SQL = "select * from certificates where name='" + name + "'";
        List<GiftCertificate> result = jdbcTemplateObject.query(SQL, new GiftMapper());
        return result.get(0);
    }

    @Override
    public List<GiftCertificate> listItems() {
        String SQL = "select * from certificates";
        return jdbcTemplateObject.query(SQL, new GiftMapper());

    }

    @Override
    public boolean deleteById(Integer id) {
        String SQL = "delete from certificates where certificate_id=" + id;
        return jdbcTemplateObject.update(SQL) > 0;
    }

    @Override
    public boolean deleteByName(String name) {
        String SQL = "delete from certificates where name='" + name + "'";
        return jdbcTemplateObject.update(SQL) > 0;
    }

    public boolean editById(int id, GiftCertificate certificate) {
        String SQL = "UPDATE certificates" +
                " SET" +
                " create_date =" + certificate.getCreate_date() +
                ", description='" + certificate.getDescription() + "'" +
                ", duration=" + certificate.getDuration() +
                ", last_update_date=" + certificate.getLast_update_date() +
                ", name='" + certificate.getName() + "'" +
                ", price=" + certificate.getPrice() +
                " WHERE certificate_id=" + id;
        return jdbcTemplateObject.update(SQL) > 0;
    }

}
