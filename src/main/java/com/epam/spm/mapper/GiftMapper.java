package com.epam.spm.mapper;

import com.epam.spm.dto.CertificateDTO;
import com.epam.spm.model.GiftCertificate;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

public class GiftMapper implements RowMapper<GiftCertificate> {
    public GiftCertificate mapRow(ResultSet rs, int rowNum) throws SQLException {
        //todo model not dto
        // add in service layer
        GiftCertificate certificate = new GiftCertificate();
        certificate.setId(rs.getInt("certificate_id"));
        certificate.setPrice(BigDecimal.valueOf(rs.getInt("price")));
        certificate.setName(rs.getString("name"));
        certificate.setDescription(rs.getString("description"));
        certificate.setDuration(rs.getInt("duration"));
        certificate.setLastUpdateDate(rs.getDate("last_update_date"));
        certificate.setCreateDate(rs.getDate("create_date"));
        List<Integer> tags=new ArrayList<>();
        tags.add(rs.getInt("tag_id"));
        certificate.setTags(tags);
        return certificate;
    }

}
