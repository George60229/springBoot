package com.epam.spm.dao.impl;

import com.epam.spm.dao.CertificateDAO;
import com.epam.spm.dto.RequestCertificateDTO;
import com.epam.spm.exception.AppNotFoundException;
import com.epam.spm.exception.ErrorCode;
import com.epam.spm.mapper.GiftMapper;
import com.epam.spm.model.GiftCertificate;
import com.epam.spm.model.Tag;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;


import javax.sql.DataSource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CertificateDAOImpl extends EntityDAOImpl implements CertificateDAO {


    public CertificateDAOImpl(DataSource dataSource) {
        setDataSource(dataSource);
    }

    final private String FIND_ALL_QUEUE = "with certificate_info as(\n" +
            "    SELECT certificates.certificate_id,create_date, description, duration, last_update_date, name, price, tag_id\n" +
            "    FROM certificates\n" +
            "             FULL JOIN certificates_to_tages on certificates.certificate_id = certificates_to_tages.certificate_id\n" +
            ")\n" +
            "            select  certificate_info.name as certificate_name ,certificate_info.certificate_id,create_date, description, duration, last_update_date, tages.name AS tag_name, price, tages.tag_id\n" +
            "            from certificate_info left join tages on certificate_info.tag_id=tages.tag_id ";


    @Override
    public List<GiftCertificate> getEntityByName(String name) {

        String SQL = "SELECT certificates.certificate_id,create_date, description, duration, last_update_date, name, price, tag_id\n" +
                "FROM certificates\n" +
                "         FULL JOIN certificates_to_tages on certificates.certificate_id = certificates_to_tages.certificate_id\n" +
                "where certificates.name='" + name + "'";
        List<GiftCertificate> result = jdbcTemplateObject.query(SQL, new GiftMapper());

        if (result.size() == 0) {
            throw new AppNotFoundException("There are no certificates with this name: " + name, ErrorCode.CERTIFICATE_NOT_FOUND);
        }


        return result;

    }

    @Override
    public List<GiftCertificate> getEntityByDescription(String description) {

        String SQL = "SELECT certificates.certificate_id,create_date, description, duration, last_update_date, name, price, tag_id\n" +
                "FROM certificates\n" +
                "         FULL JOIN certificates_to_tages on certificates.certificate_id = certificates_to_tages.certificate_id\n" +
                "where certificates.name='" + description + "'";

        return jdbcTemplateObject.query(SQL, new GiftMapper());

    }


    @Override
    public List<GiftCertificate> listItems() {
        String sqlQueue = FIND_ALL_QUEUE + "order by certificate_info.name ASC ";
        return jdbcTemplateObject.query(sqlQueue, new GiftMapper());

    }

    @Override
    public boolean deleteById(Integer id) {
        String SQL = "delete from certificates where certificate_id=" + id;
        if (jdbcTemplateObject.update(SQL) == 0) {
            throw new AppNotFoundException("Certificate with this id " + id + "is not deleted:", ErrorCode.CERTIFICATE_NOT_FOUND);
        }
        return true;
    }

    @Override
    public boolean deleteByName(String name) {
        String SQL = "delete from certificates where name='" + name + "'";
        if (jdbcTemplateObject.update(SQL) == 0) {
            throw new AppNotFoundException("Certificate with this name " + name + "is not deleted:", ErrorCode.CERTIFICATE_NOT_FOUND);
        }
        return true;
    }

    @Override
    public RequestCertificateDTO editById(int id, RequestCertificateDTO certificate) {
        String sqlQueue = "UPDATE certificates" + " SET";


        sqlQueue += " last_update__date =" + LocalDateTime.now() + ",";

        if (certificate.getDescription() != null) {
            sqlQueue += " description =" + certificate.getDescription() + ",";
        }
        if (certificate.getDuration() != 0) {
            sqlQueue += " duration=" + certificate.getDuration() + ",";
        }
        if (certificate.getName() != null) {
            sqlQueue += " name='" + certificate.getName() + "',";
        }
        if (certificate.getPrice() != null) {
            sqlQueue += " price='" + certificate.getName() + "',";
        }
        sqlQueue = sqlQueue.substring(0, sqlQueue.length() - 1);
        if (jdbcTemplateObject.update(sqlQueue) == 0) {
            throw new AppNotFoundException("Field is not edited" + certificate, ErrorCode.CERTIFICATE_NOT_FOUND);
        }
        return certificate;
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

    @Override
    public List<GiftCertificate> findByTagName(String tagName) {
        String sqlQueue = "WITH info AS (\n" +
                "    SELECT certificates.certificate_id,create_date, description, duration, last_update_date, name, price,tag_id\n" +
                "    FROM certificates\n" +
                "             FULL JOIN certificates_to_tages on certificates.certificate_id = certificates_to_tages.certificate_id\n" +
                ")\n" +
                "SELECT info.certificate_id,create_date, description, duration, last_update_date, info.name, price,tages.tag_id, tages.name\n" +
                "FROM info\n" +
                "         FULL JOIN tages on info.tag_id = tages.tag_id where tages.name='" + tagName + "'";
        return jdbcTemplateObject.query(sqlQueue, new GiftMapper());

    }

    @Override
    public GiftCertificate getCertificateById(int id) {
        String sqlQueue = "select * from certificate where id=" + id;
        return jdbcTemplateObject.queryForObject(sqlQueue, new GiftMapper());

    }

    @Override
    public GiftCertificate createCertificate(RequestCertificateDTO certificateDTO) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("certificates").usingGeneratedKeyColumns("certificate_id");

        Map<String, Object> parameters = new HashMap<>(1);
        parameters.put("description",certificateDTO.getDescription());
        parameters.put("duration",certificateDTO.getDuration());
        parameters.put("price",certificateDTO.getPrice());
        parameters.put("name",certificateDTO.getName());
        int newId = (int) simpleJdbcInsert.executeAndReturnKey(parameters);
        GiftCertificate result=new GiftCertificate();
        result.setName(certificateDTO.getName());
        result.setPrice(certificateDTO.getPrice());
        result.setDuration(certificateDTO.getDuration());
        result.setDescription(certificateDTO.getDescription());
        result.setLastUpdateDate(LocalDate.now());
        result.setCreateDate(LocalDate.now());
        result.setId(newId);
        return result;
    }
}
