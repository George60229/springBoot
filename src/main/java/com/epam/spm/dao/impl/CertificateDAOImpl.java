package com.epam.spm.dao.impl;

import com.epam.spm.dao.CertificateDAO;
import com.epam.spm.exception.AppNotFoundException;
import com.epam.spm.exception.ErrorCode;

import com.epam.spm.mapper.GiftMapper;
import com.epam.spm.model.GiftCertificate;
import com.epam.spm.dao.enumeration.CertificateParameters;
import com.epam.spm.utils.SortParameter;
import com.epam.spm.utils.SortWay;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;


import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.*;

public class CertificateDAOImpl extends EntityDAOImpl implements CertificateDAO {


    public CertificateDAOImpl(DataSource dataSource) {
        setDataSource(dataSource);
    }

    final private String FIND_ALL_QUEUE = "with certificate_info as(\n" +
            "    SELECT certificates.certificate_id,create_date, description, duration, last_update_date, name, price, tag_id\n" +
            "    FROM certificates\n" +
            "             FULL JOIN certificates_to_tages on certificates.certificate_id = certificates_to_tages.certificate_id\n" +
            ")\n" +
            "            select  certificate_info.name as certificate_name ,certificate_info.certificate_id,create_date," +
            " description, duration, last_update_date, tages.name AS tag_name, price, tages.tag_id\n" +
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
    public List<GiftCertificate> listItems(SortParameter parameter, SortWay sortWay) {
        if (parameter == null) {
            parameter = SortParameter.NAME;
        }
        if (sortWay == null) {
            sortWay = SortWay.ASC;
        }
        String sqlQueue = "";
        if (parameter.name().equals("NAME")) {
            sqlQueue = FIND_ALL_QUEUE + "order by certificate_info.name " + sortWay;
        }
        if (parameter.name().equals("DATE")) {
            sqlQueue = FIND_ALL_QUEUE + "order by certificate_info.create_date " + sortWay;
        }


        return removeSame(jdbcTemplateObject.query(sqlQueue, new GiftMapper()));

    }

    @Override
    public List<GiftCertificate> getCertificateByDescription(String description) {
        String SQL = "SELECT certificates.certificate_id,create_date, description, duration, last_update_date, name, price, tag_id\n" +
                "FROM certificates\n" +
                "         FULL JOIN certificates_to_tages on certificates.certificate_id = certificates_to_tages.certificate_id\n" +
                "where certificates.name='" + description + "'";

        return jdbcTemplateObject.query(SQL, new GiftMapper());
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
    public GiftCertificate update(GiftCertificate certificate) {

        String getCreateDateSql = FIND_ALL_QUEUE + " where certificate_id=" + certificate.getId();

        GiftCertificate giftCertificateFromDB = jdbcTemplateObject.queryForObject(getCreateDateSql, new GiftMapper());
//fix
        if (giftCertificateFromDB == null) {
            throw new AppNotFoundException("Certificate with this id " + certificate.getId() + "is not found", ErrorCode.CERTIFICATE_NOT_FOUND);
        }
        //service layer


        String sqlQueue = "UPDATE certificates" + " SET";
        LocalDateTime date = LocalDateTime.now();
        sqlQueue += " last_update_date ='" + date + "',";
        sqlQueue += " description ='" + certificate.getDescription() + "',";
        sqlQueue += " duration=" + certificate.getDuration() + ",";
        sqlQueue += " name='" + certificate.getName() + "',";
        sqlQueue += " price='" + certificate.getPrice() + "',";
        sqlQueue = sqlQueue.substring(0, sqlQueue.length() - 1);
        sqlQueue += " where certificate_id=" + certificate.getId();
        jdbcTemplateObject.update(sqlQueue);

        String getEntitySql = FIND_ALL_QUEUE + " where certificate_id=" + certificate.getId();

        return jdbcTemplateObject.queryForObject(getEntitySql, new GiftMapper());
    }


    @Override
    public List<GiftCertificate> findByTagName(String tagName) {
        String sqlQueue = FIND_ALL_QUEUE + " where tages.name='" + tagName + "'";
        return jdbcTemplateObject.query(sqlQueue, new GiftMapper());

    }

    @Override
    public Optional<GiftCertificate> getCertificateById(int id) {
        String sqlQueue = FIND_ALL_QUEUE + " where certificate_id=" + id;
        return Optional.ofNullable(fixToOne(jdbcTemplateObject.query(sqlQueue, new GiftMapper())));

    }

    @Override
    public GiftCertificate createCertificate(GiftCertificate giftCertificate) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("certificates").usingGeneratedKeyColumns("certificate_id");

        Map<String, Object> parameters = new HashMap<>(1);
        parameters.put(CertificateParameters.DESCRIPTION.getMessage(), giftCertificate.getDescription());
        parameters.put(CertificateParameters.DURATION.getMessage(), giftCertificate.getDuration());
        parameters.put(CertificateParameters.PRICE.getMessage(), giftCertificate.getPrice());
        parameters.put(CertificateParameters.NAME.getMessage(), giftCertificate.getName());
        LocalDateTime date = LocalDateTime.now();
        parameters.put(CertificateParameters.CREATE_DATE.getMessage(), date);
        int newId = (int) simpleJdbcInsert.executeAndReturnKey(parameters);


        String sqlQueue = FIND_ALL_QUEUE + " where certificate_id=" + newId;
        return jdbcTemplateObject.queryForObject(sqlQueue, new GiftMapper());
    }

    private List<GiftCertificate> removeSame(List<GiftCertificate> certificates) {

        List<GiftCertificate> result = new ArrayList<>();
        Set<Integer> idList = new HashSet<>();
        certificates.sort(Comparator.comparing(GiftCertificate::getId));

        for (GiftCertificate certificate : certificates) {
            if (!idList.contains(certificate.getId())) {
                idList.add(certificate.getId());
                result.add(certificate);
            } else {
                int res = idList.size() - 1;
                GiftCertificate newCertificate = result.get(res);
                result.remove(res);
                newCertificate.addTag(certificate.getTags().get(0));
                result.add(newCertificate);
            }
        }
        return result;

    }

    private GiftCertificate fixToOne(List<GiftCertificate> certificates) {
        if(certificates.size()==0){
            return null;
        }

        GiftCertificate result = certificates.get(0);




        for (GiftCertificate certificate : certificates) {

            result.addTag(certificate.getTags().get(0));
        }

        return result;

    }


}
