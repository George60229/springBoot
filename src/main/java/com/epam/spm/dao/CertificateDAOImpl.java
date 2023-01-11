package com.epam.spm.dao;

import com.epam.spm.dto.CertificateDTO;
import com.epam.spm.dto.CreateCertificateDTO;
import com.epam.spm.exception.CertificateNotFoundException;
import com.epam.spm.model.GiftCertificate;
import com.epam.spm.mapper.GiftMapper;
import com.epam.spm.service.CertificateService;
import com.epam.spm.service.CertificateServiceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CertificateDAOImpl extends EntityDAOImpl implements CertificateDAO {
    final CertificateService service = new CertificateServiceImpl();



    final private String FIND_ALL_QUEUE = "SELECT certificates.certificate_id,create_date, description, duration, last_update_date, name, price, tag_id\n" +
            "FROM certificates\n" +
            "         FULL JOIN certificates_to_tages on certificates.certificate_id = certificates_to_tages.certificate_id\n";


    public CreateCertificateDTO create(CreateCertificateDTO certificate) {

        String sqlQuery = "insert into certificates (name, price,create_date,description,duration,last_update_date) values (?, ?,?,?,?,?)";
        jdbcTemplateObject.update(sqlQuery, certificate.getName(), certificate.getPrice(), LocalDateTime.now(), certificate.getDescription(), certificate.getDuration(), LocalDateTime.now());
        return certificate;

    }

    @Override
    public CertificateDTO getEntityByName(String name) {
        //model to db
        //dto to front
        String SQL = "SELECT certificates.certificate_id,create_date, description, duration, last_update_date, name, price, tag_id\n" +
                "FROM certificates\n" +
                "         FULL JOIN certificates_to_tages on certificates.certificate_id = certificates_to_tages.certificate_id\n" +
                "where certificates.name='" + name + "'";
        List<CertificateDTO> result = service.convertToDTO(jdbcTemplateObject.query(SQL, new GiftMapper()));

        if (result.size() == 0) {
            throw new CertificateNotFoundException("There are no certificates with this name: " + name);
        }
        List<Integer> tags = new ArrayList<>();
        for (CertificateDTO certificate : result) {
            tags.add(certificate.getTags().get(0));
        }
        result.get(0).setTags(tags);
        return result.get(0);

    }

    @Override
    public CertificateDTO getEntityByDescription(String description) {
        //model to db
        //dto to front
        //todo
        String SQL = "SELECT certificates.certificate_id,create_date, description, duration, last_update_date, name, price, tag_id\n" +
                "FROM certificates\n" +
                "         FULL JOIN certificates_to_tages on certificates.certificate_id = certificates_to_tages.certificate_id\n" +
                "where certificates.name='" + description + "'";
        List<CertificateDTO> result = service.convertToDTO(jdbcTemplateObject.query(SQL, new GiftMapper()));
        if (result.size() == 0) {
            throw new CertificateNotFoundException("There are no certificates with this description: " + description);
        }
        List<Integer> tags = new ArrayList<>();
        for (CertificateDTO certificate : result) {
            tags.add(certificate.getTags().get(0));
        }
        result.get(0).setTags(tags);
        return result.get(0);

    }


    @Override
    public List<CertificateDTO> listItems() {
        String sqlQueue = FIND_ALL_QUEUE + "order by name ASC ";
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
    public boolean editById(int id, CreateCertificateDTO certificate) {
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
        return true;
    }

    @Override
    public List<CertificateDTO> listItemsDESC() {
        String sqlQueue = FIND_ALL_QUEUE + "order by create_date DESC ";
        return service.convertToDTO(jdbcTemplateObject.query(sqlQueue, new GiftMapper()));

    }

    @Override
    public List<CertificateDTO> listItemsDateASC() {
        String sqlQueue = FIND_ALL_QUEUE + "order by create_date ASC ";
        return service.convertToDTO(jdbcTemplateObject.query(sqlQueue, new GiftMapper()));

    }

    @Override
    public List<CertificateDTO> listItemsDateDESC() {
        String sqlQueue = FIND_ALL_QUEUE + "order by create_date DESC ";
        return service.convertToDTO(jdbcTemplateObject.query(sqlQueue, new GiftMapper()));

    }

    @Override
    public List<CertificateDTO> findByTagName(String tagName) {
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
}
