package com.epam.spm.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


public class GiftCertificate {


    private Integer certificateId;
    private String name;
    private String description;
    private BigDecimal price;
    private int duration;

    private LocalDateTime createDate;

    private LocalDateTime lastUpdateDate;

    private List<String> tags;




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

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime create_date) {
        this.createDate = create_date;
    }

    public LocalDateTime getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(LocalDateTime lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public void setTag(String tag_name) {
        this.tags.add(tag_name);
    }


}

