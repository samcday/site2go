package com.site2go.dao.entities;

import com.google.common.base.Objects;
import com.google.common.collect.Sets;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user")
@NamedQueries({
    @NamedQuery(
        name = "findUserByEmail",
        query = "select u from UserEntity u where u.email = :email"
    )
})
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "user_id_seq_gen")
    @SequenceGenerator(name = "user_id_seq_gen", sequenceName = "user_id_seq")
    private Integer id;

    private String email;
    private String password;

    private Boolean superAdmin;

    @ManyToMany(mappedBy = "users")
    Set<SiteEntity> sites = Sets.newHashSet();

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getSuperAdmin() {
        return superAdmin;
    }

    public void setSuperAdmin(Boolean superAdmin) {
        this.superAdmin = superAdmin;
    }

    public Set<SiteEntity> getSites() {
        return sites;
    }

    public void setSites(Set<SiteEntity> sites) {
        this.sites = sites;
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
        if(!(obj instanceof UserEntity)) return false;
        UserEntity other = (UserEntity)obj;
        return Objects.equal(this.email, other.email);
    }

    @Override
    public int hashCode() {
        return this.email.hashCode();
    }

    @PreUpdate
    @PrePersist
    private void modifyTimestamps() {
        if(this.createdDate == null) this.createdDate = new DateTime();
        this.modifiedDate = new DateTime();
    }
}
