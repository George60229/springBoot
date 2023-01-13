package com.epam.spm.dao.impl;

import com.epam.spm.converter.CertificateConverter;
import com.epam.spm.converter.impl.CertificateConverterImpl;
import com.epam.spm.dao.CertificateDAO;
import com.epam.spm.dto.ResponseCertificateDTO;
import com.epam.spm.dto.RequestCertificateDTO;
import com.epam.spm.exception.CertificateNotFoundException;
import com.epam.spm.mapper.GiftMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.List;

public class CertificateDAOImpl extends EntityDAOImpl implements CertificateDAO {

    CertificateConverter service=new CertificateConverterImpl();


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
    public List<ResponseCertificateDTO> getEntityByName(String name) {

        String SQL = "SELECT certificates.certificate_id,create_date, description, duration, last_update_date, name, price, tag_id\n" +
                "FROM certificates\n" +
                "         FULL JOIN certificates_to_tages on certificates.certificate_id = certificates_to_tages.certificate_id\n" +
                "where certificates.name='" + name + "'";
        List<ResponseCertificateDTO> result = service.convertToDTO(jdbcTemplateObject.query(SQL, new GiftMapper()));

        if (result.size() == 0) {
            throw new CertificateNotFoundException("There are no certificates with this name: " + name);
        }


        return result;

    }

    @Override
    public List<ResponseCertificateDTO> getEntityByDescription(String description) {

        String SQL = "SELECT certificates.certificate_id,create_date, description, duration, last_update_date, name, price, tag_id\n" +
                "FROM certificates\n" +
                "         FULL JOIN certificates_to_tages on certificates.certificate_id = certificates_to_tages.certificate_id\n" +
                "where certificates.name='" + description + "'";

        return service.convertToDTO(jdbcTemplateObject.query(SQL, new GiftMapper()));

    }


    @Override
    public List<ResponseCertificateDTO> listItems() {
        String sqlQueue = FIND_ALL_QUEUE + "order by certificate_info.name ASC ";
        return service.convertToDTO(jdbcTemplateObject.query(sqlQueue, new GiftMapper()));

    }

    @Override
    public boolean deleteById(Integer id) {
        String SQL = "delete from certificates where certificate_id=" + id;
        if (jdbcTemplateObject.update(SQL) == 0) {
            throw new CertificateNotFoundException("Certificate with this id " + id + "is not deleted:");
        }
        return true;
    }

    @Override
    public boolean deleteByName(String name) {
        String SQL = "delete from certificates where name='" + name + "'";
        if (jdbcTemplateObject.update(SQL) == 0) {
            throw new CertificateNotFoundException("Certificate with this name " + name + "is not deleted:");
        }
        return true;
    }

    @Override
    public ResponseCertificateDTO editById(int id, RequestCertificateDTO certificate) {
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
            throw new CertificateNotFoundException("Field is not edited" + certificate);
        }
        return service.convertRequestToResponse(certificate);
    }

    @Override
    public List<ResponseCertificateDTO> listItemsDESC() {
        String sqlQueue = FIND_ALL_QUEUE + "order by create_date DESC ";
        return service.convertToDTO(jdbcTemplateObject.query(sqlQueue, new GiftMapper()));

    }

    @Override
    public List<ResponseCertificateDTO> listItemsDateASC() {
        String sqlQueue = FIND_ALL_QUEUE + "order by create_date ASC ";
        return service.convertToDTO(jdbcTemplateObject.query(sqlQueue, new GiftMapper()));

    }

    @Override
    public List<ResponseCertificateDTO> listItemsDateDESC() {
        String sqlQueue = FIND_ALL_QUEUE + "order by create_date DESC ";
        return service.convertToDTO(jdbcTemplateObject.query(sqlQueue, new GiftMapper()));

    }

    @Override
    public List<ResponseCertificateDTO> findByTagName(String tagName) {
        String sqlQueue = "WITH info AS (\n" +
                "    SELECT certificates.certificate_id,create_date, description, duration, last_update_date, name, price,tag_id\n" +
                "    FROM certificates\n" +
                "             FULL JOIN certificates_to_tages on certificates.certificate_id = certificates_to_tages.certificate_id\n" +
                ")\n" +
                "SELECT info.certificate_id,create_date, description, duration, last_update_date, info.name, price,tages.tag_id, tages.name\n" +
                "FROM info\n" +
                "         FULL JOIN tages on info.tag_id = tages.tag_id where tages.name='" + tagName + "'";
        return service.convertToDTO(jdbcTemplateObject.query(sqlQueue, new GiftMapper()));

    }

    @Override
    public ResponseCertificateDTO getCertificateById(int id) {
        String sqlQueue = "select * from certificate where id=" + id;
        return service.convertOneToDTO(jdbcTemplateObject.queryForObject(sqlQueue, new GiftMapper()));

    }

    @Override
    public ResponseCertificateDTO createCertificate(RequestCertificateDTO certificateDTO) {
        String sqlQuery = "insert into certificates (name, price,create_date,description,duration,last_update_date) values (?, ?,?,?,?,?)";
        jdbcTemplateObject.update(sqlQuery, certificateDTO.getName(), certificateDTO.getPrice(), LocalDateTime.now(), certificateDTO.getDescription(), certificateDTO.getDuration(), LocalDateTime.now());
        return service.convertRequestToResponse(certificateDTO) ;
    }
}
