package com.epam.spm.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;


public class GiftCertificate {


    private Integer certificateId;
    private String name;
    private String description;
    private BigDecimal price;
    private int duration;

    private Date createDate;

    private Date lastUpdateDate;

    private List<Integer> tags;

    //todo sql create


    public Integer getId() {
        return certificateId;
    }

    public void setId(Integer id) {
        this.certificateId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date create_date) {
        this.createDate = create_date;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public List<Integer> getTags() {
        return tags;
    }

    public void setTags(List<Integer> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "GiftCertificate{" +
                "certificateId=" + certificateId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", duration=" + duration +
                ", createDate=" + createDate +
                ", lastUpdateDate=" + lastUpdateDate +
                ", tags=" + tags +
                '}';
    }
}
