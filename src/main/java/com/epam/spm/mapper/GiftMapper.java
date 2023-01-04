package com.epam.spm.mapper;

import com.epam.spm.model.GiftCertificate;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GiftMapper implements RowMapper<GiftCertificate> {
    public GiftCertificate mapRow(ResultSet rs, int rowNum) throws SQLException {
        GiftCertificate certificate = new GiftCertificate();
        certificate.setPrice(rs.getInt("price"));
        certificate.setName(rs.getString("name"));
        certificate.setDescription(rs.getString("description"));
        certificate.setDuration(rs.getInt("duration"));
        certificate.setLast_update_date(rs.getDate("last_update_date"));
        certificate.setCreate_date(rs.getDate("create_date"));
        return certificate;
    }

}
