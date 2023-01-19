package com.epam.spm.dto.request;

import com.epam.spm.utils.FindParameter;
import com.epam.spm.utils.SortParameter;
import com.epam.spm.utils.SortWay;

public class CertificateFindByDTO {
    private SortWay sortWay = SortWay.ASC;
    private SortParameter sortParameter = SortParameter.DATE;

    private FindParameter findParameter = FindParameter.DEFAULT;
    private String value = "";

    public SortWay getSortWay() {
        return sortWay;
    }

    public SortParameter getSortParameter() {
        return sortParameter;
    }

    public FindParameter getFindParameter() {
        return findParameter;
    }

    public void setSortWay(SortWay sortWay) {
        this.sortWay = sortWay;
    }

    public void setSortParameter(SortParameter sortParameter) {
        this.sortParameter = sortParameter;
    }

    public void setFindParameter(FindParameter findParameter) {
        this.findParameter = findParameter;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
