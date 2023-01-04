package com.epam.spm.dto;

import java.util.Date;

public class CertificateDTO {
    //required parameters
    private String name;


    //optional parameters
    private Date create_date;
    private String description;
    private int duration;
    private Date last_update_date;
    private int price;


    private CertificateDTO(CertificateBuilder builder) {
        this.name = builder.name;

    }

    public String getName() {
        return name;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public String getDescription() {
        return description;
    }

    public int getDuration() {
        return duration;
    }

    public Date getLast_update_date() {
        return last_update_date;
    }

    public int getPrice() {
        return price;
    }

    //Builder Class
    public static class CertificateBuilder {

        // required parameters
        private String name;


        //optional parameters
        private Date create_date;
        private String description;
        private int duration;
        private Date last_update_date;
        private int price;

        public CertificateBuilder(String name) {
            this.name = name;
        }

        public CertificateBuilder setCreateDate(Date create_date) {
            this.create_date = create_date;
            return this;
        }

        public CertificateBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public CertificateBuilder setDuration(int duration) {
            this.duration = duration;
            return this;
        }

        public CertificateBuilder setLastUpdate(Date last_update_date) {
            this.last_update_date = last_update_date;
            return this;
        }

        public CertificateBuilder setPrice(int price) {
            this.price = price;
            return this;
        }


        public CertificateDTO build() {
            return new CertificateDTO(this);
        }

    }
}
