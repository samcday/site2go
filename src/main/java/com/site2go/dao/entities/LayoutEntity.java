package com.site2go.dao.entities;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;

@Entity
@Table(name = "layout")
@NamedQueries({
    @NamedQuery(
        name = "findLayoutBySite",
        query = "select l from LayoutEntity l where l.site.id = :id"
    ),
    @NamedQuery(
        name = "findLayoutBySiteAndSlug",
        query = "select l from LayoutEntity l where l.site.id = :id and l.slug = :slug"
    )
})
public class LayoutEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "layout_id_seq_gen")
    @SequenceGenerator(name = "layout_id_seq_gen", sequenceName = "layout_id_seq")
    private Integer id;

    private String slug;
    private String name;

    @Lob
    private String template;

    @ManyToOne
    private SiteEntity site;

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

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public SiteEntity getSite() {
        return site;
    }

    public void setSite(SiteEntity site) {
        this.site = site;
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
}
