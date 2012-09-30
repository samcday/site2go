package com.site2go.dao.repositories.jpa;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class SiteRepository {
    @PersistenceContext
    private EntityManager entityManager;


}
