package com.epam.spm.dao;

import com.epam.spm.mapper.TagMapper;
import com.epam.spm.model.EntityDAO;
import com.epam.spm.model.GiftCertificate;
import com.epam.spm.mapper.GiftMapper;
import com.epam.spm.model.Tag;
import org.springframework.core.metrics.StartupStep;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CertificatesJDBCTemplate extends AbstractJDBCTemplate implements EntityDAO<GiftCertificate> {



    public GiftCertificate create(GiftCertificate certificate) {
        String sqlQuery = "insert into certificates (name, price,create_date,description,duration,last_update_date) values (?, ?,?,?,?,?)";
        jdbcTemplateObject.update(sqlQuery, certificate.getName(), certificate.getPrice(), certificate.getCreateDate(), certificate.getDescription(), certificate.getDuration(), certificate.getLastUpdateDate());
        return certificate;

    }

    @Override
    public GiftCertificate getEntityByName(String name) {
        //model to db
        //dto to front
        String SQL ="SELECT certificates.certificate_id,create_date, description, duration, last_update_date, name, price, tag_id\n" +
                "FROM certificates\n" +
                "         FULL JOIN certificates_to_tages on certificates.certificate_id = certificates_to_tages.certificate_id\n" +
                "where certificates.name='"+name+"'";
        List<GiftCertificate> result = jdbcTemplateObject.query(SQL, new GiftMapper());
        List<Integer> tags=new ArrayList<>();
        for (GiftCertificate certificate : result) {
            tags.add(certificate.getTags().get(0));
        }
        result.get(0).setTags(tags);
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
        if(jdbcTemplateObject.update(SQL) == 0){
           throwEx();
        }
        return true;
    }

    @Override
    public boolean deleteByName(String name) {
        String SQL = "delete from certificates where name='" + name + "'";
        if(jdbcTemplateObject.update(SQL) == 0){
            throwEx();
        }
        return true;
    }

    public boolean editById(int id, GiftCertificate certificate) {
        String SQL = "UPDATE certificates" +
                " SET" +
                " create_date =" + certificate.getCreateDate() +
                ", description='" + certificate.getDescription() + "'" +
                ", duration=" + certificate.getDuration() +
                ", last_update_date=" + certificate.getLastUpdateDate() +
                ", name='" + certificate.getName() + "'" +
                ", price=" + certificate.getPrice() +
                " WHERE certificate_id=" + id;
        return jdbcTemplateObject.update(SQL) > 0;
    }
    private void throwEx(){
        throw new IllegalArgumentException("Tag with this name is not found");
    }

}
