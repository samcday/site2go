package com.site2go.dao.entities;

import com.google.common.base.Objects;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;

@Entity
@Table(name = "site")
@NamedQueries({
    @NamedQuery(
        name = "findSiteByDomain",
        query = "select s from SiteEntity s where s.domain = :domain"
    )
})
public class SiteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "site_id_seq_gen")
    @SequenceGenerator(name = "site_id_seq_gen", sequenceName = "site_id_seq")
    private Integer id;
    private String name;
    private String domain;

    @ManyToOne
    private LayoutEntity defaultLayout;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime modifiedDate;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime createdDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public LayoutEntity getDefaultLayout() {
        return defaultLayout;
    }

    public void setDefaultLayout(LayoutEntity defaultLayout) {
        this.defaultLayout = defaultLayout;
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

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof SiteEntity)) return false;
        SiteEntity other = (SiteEntity)obj;
        return Objects.equal(this.domain, other.domain);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.domain);
    }
}
