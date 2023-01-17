package com.epam.spm.dto.request;

import java.math.BigDecimal;

public class CertificateRequestDTO {
    private String name;
    private String description;
    private BigDecimal price;
    private int duration;
    //todo request rename

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

}
