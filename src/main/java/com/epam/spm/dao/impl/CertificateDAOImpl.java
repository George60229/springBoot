package com.epam.spm.dao.impl;

import com.epam.spm.dao.CertificateDAO;
import com.epam.spm.dto.request.CertificateFindByDTO;
import com.epam.spm.exception.BadRequestException;
import com.epam.spm.exception.ErrorCode;

import com.epam.spm.mapper.GiftMapper;
import com.epam.spm.mapper.TagMapper;
import com.epam.spm.model.GiftCertificate;
import com.epam.spm.dao.enumeration.CertificateParameters;
import com.epam.spm.model.Tag;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;


import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.*;

public class CertificateDAOImpl extends EntityDAOImpl implements CertificateDAO {


    public CertificateDAOImpl(DataSource dataSource) {
        setDataSource(dataSource);
    }

    final private String FIND_ALL_QUEUE = "with certificate_info as(\n" +
            "    SELECT certificates.certificate_id,create_date, description, duration, last_update_date, name," +
            " price, " +
            "tag_id\n" +
            "    FROM certificates\n" +
            "              left JOIN certificates_to_tages on" +
            " certificates.certificate_id = certificates_to_tages.certificate_id\n" +
            ")\n" +
            "            select  " +
            "certificate_info.name as certificate_name ," +
            "certificate_info.certificate_id," +
            "create_date," +
            " description, " +
            "duration, " +
            "last_update_date, " +
            "tages.name AS tag_name, " +
            "price, " +
            "tages.tag_id\n" +
            "            from certificate_info left join tages on certificate_info.tag_id=tages.tag_id ";


    @Override
    public List<GiftCertificate> listItems(CertificateFindByDTO certificateFindByDTO) {
        if (!certificateFindByDTO.getFindParameter().name().equals("DEFAULT")
                && certificateFindByDTO.getValue().equals("")) {
            throw new BadRequestException("Value should be not null", ErrorCode.BAD_REQUEST_ERROR);
        }
        String sqlQueue;
        if (certificateFindByDTO.getFindParameter().name().equals("DESCRIPTION")) {
            sqlQueue = FIND_ALL_QUEUE + " where certificate_info.description='" + certificateFindByDTO.getValue() + "'";
        } else if (certificateFindByDTO.getFindParameter().name().equals("NAME")) {
            sqlQueue = FIND_ALL_QUEUE + " where certificate_info.name='" + certificateFindByDTO.getValue() + "'";
        } else {
            sqlQueue = FIND_ALL_QUEUE;
        }
        if (certificateFindByDTO.getSortParameter().name().equals("NAME")) {
            sqlQueue += " order by certificate_info.name " + certificateFindByDTO.getSortWay();
        } else if (certificateFindByDTO.getSortParameter().name().equals("DATE")) {
            sqlQueue += " order by certificate_info.create_date " + certificateFindByDTO.getSortWay();
        }
        return removeSame(jdbcTemplateObject.query(sqlQueue, new GiftMapper()));
    }


    @Override
    public boolean deleteById(Integer id) {
        String sql="delete from certificates_to_tages where certificate_id="+id;
        jdbcTemplateObject.update(sql);
        String SQL = "delete from certificates where certificate_id=" + id;
        return jdbcTemplateObject.update(SQL) > 0;
    }



    @Override
    public GiftCertificate update(GiftCertificate certificate) {
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
        addTags(certificate);
        String getEntitySql = FIND_ALL_QUEUE + " where certificate_id=" + certificate.getId();
        return fixToOne(jdbcTemplateObject.query(getEntitySql, new GiftMapper()));
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
        giftCertificate.setId(newId);
        addTags(giftCertificate);
        String sqlQueue = FIND_ALL_QUEUE + " where certificate_id=" + newId;
        return fixToOne(jdbcTemplateObject.query(sqlQueue, new GiftMapper()));
    }

    private List<GiftCertificate> removeSame(List<GiftCertificate> certificates) {
        List<GiftCertificate> result = new ArrayList<>();
        Set<Integer> idList = new HashSet<>();
        for (GiftCertificate certificate : certificates) {
            //todo foreach to stream
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
        if (certificates.size() == 0) {
            return null;
        }
        GiftCertificate result = certificates.get(0);
        for (GiftCertificate certificate : certificates) {
            result.addTag(certificate.getTags().get(0));
        }
        return result;
    }

    private void addTags(GiftCertificate giftCertificate) {

        List<Tag> tagList = new ArrayList<>();

        if (giftCertificate.getTags().size() != 0) {
            if (giftCertificate.getTags().get(0) == null) {
                String deleteCertificatesToTagsSql = "delete from certificates_to_tages where certificate_id=" +
                        giftCertificate.getId();
                jdbcTemplateObject.update(deleteCertificatesToTagsSql);
                return;
            }
            for (int i = 0; i < giftCertificate.getTags().size(); i++) {
                String tagSQL = "select * from tages where name='" + giftCertificate.getTags().get(i) + "'";

                if (jdbcTemplateObject.query(tagSQL, new TagMapper()).isEmpty()) {
                    String insertTagSQL = "insert into tages (name) values ('" + giftCertificate.getTags().get(i) + "')";
                    jdbcTemplateObject.update(insertTagSQL);
                }
                tagList.addAll(jdbcTemplateObject.query(tagSQL, new TagMapper()));
            }
            String deleteCertificatesToTagsSql = "delete from certificates_to_tages where certificate_id=" +
                    giftCertificate.getId();
            jdbcTemplateObject.update(deleteCertificatesToTagsSql);
            for (Tag tag : tagList) {
                String insertToTagsToCertificates = "insert into certificates_to_tages(certificate_id,tag_id) " +
                        "values(" + giftCertificate.getId() + "," + tag.getId() + ")";
                jdbcTemplateObject.update(insertToTagsToCertificates);
            }
        }
    }


}
