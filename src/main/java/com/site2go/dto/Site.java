package com.site2go.dto;

import org.joda.time.DateTime;

public class Site {
    private String name;
    private String domain;
    private DateTime modifiedDate;
    private DateTime createdDate;
    private Integer defaultLayout;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public DateTime getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(DateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public DateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(DateTime createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getDefaultLayout() {
        return defaultLayout;
    }

    public void setDefaultLayout(Integer defaultLayout) {
        this.defaultLayout = defaultLayout;
    }
}
