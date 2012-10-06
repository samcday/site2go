package com.site2go.dao.entities;

import com.google.common.base.Objects;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;

@Entity
@Table(name = "page")
@NamedQueries({
    @NamedQuery(
        name = "findPageBySiteAndSlug",
        query = "select p from PageEntity p where p.site.id = :siteId and p.slug = :slug"
    )
})
public class PageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "page_id_seq_gen")
    @SequenceGenerator(name = "page_id_seq_gen", sequenceName = "page_id_seq")
    private Integer id;

    private String slug;
    private String title;
    private String metaTitle;
    private String metaDescription;
    private String metaKeywords;

    @ManyToOne
    private SiteEntity site;

    @ManyToOne
    private LayoutEntity layout;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMetaTitle() {
        return metaTitle;
    }

    public void setMetaTitle(String metaTitle) {
        this.metaTitle = metaTitle;
    }

    public String getMetaDescription() {
        return metaDescription;
    }

    public void setMetaDescription(String metaDescription) {
        this.metaDescription = metaDescription;
    }

    public String getMetaKeywords() {
        return metaKeywords;
    }

    public void setMetaKeywords(String metaKeywords) {
        this.metaKeywords = metaKeywords;
    }

    public SiteEntity getSite() {
        return site;
    }

    public void setSite(SiteEntity site) {
        this.site = site;
    }

    public LayoutEntity getLayout() {
        return layout;
    }

    public void setLayout(LayoutEntity layout) {
        this.layout = layout;
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
        if(!(obj instanceof PageEntity)) return false;
        PageEntity other = (PageEntity)obj;
        return Objects.equal(this.site, other.site)
            && Objects.equal(this.slug, other.slug);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.site, this.slug);
    }
}
