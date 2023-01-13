package com.epam.spm.mapper;

import com.epam.spm.model.GiftCertificate;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class GiftMapper implements RowMapper<GiftCertificate> {
    public GiftCertificate mapRow(ResultSet rs, int rowNum) throws SQLException {

        GiftCertificate certificate = new GiftCertificate();
        certificate.setId(rs.getInt("certificate_id"));
        certificate.setPrice(BigDecimal.valueOf(rs.getInt("price")));
        certificate.setName(rs.getString("certificate_name"));
        certificate.setDescription(rs.getString("description"));
        certificate.setDuration(rs.getInt("duration"));
        List<String> tagIdList = new ArrayList<>();
        String tagName = rs.getString("tag_name");
        tagIdList.add(tagName);
        certificate.setTags(tagIdList);
        //todo
        //rs.getTimestamp().toLocalDateTime();
        certificate.setCreateDate(rs.getTimestamp("create_date").toLocalDateTime());
        certificate.setLastUpdateDate(rs.getTimestamp("last_update_date").toLocalDateTime());


        return certificate;
    }

}
