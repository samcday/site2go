package com.site2go.dao.repositories.jpa;

import com.site2go.dao.repositories.GenericRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@Transactional(propagation = Propagation.MANDATORY, noRollbackFor = {EmptyResultDataAccessException.class})
public abstract class GenericRepositoryImpl<T> implements GenericRepository<T> {
    @PersistenceContext
    protected EntityManager entityManager;

    Class<T> clazz;

    protected GenericRepositoryImpl() {
        // Can't believe how long I haven't known about this: the following two lines determine the Class of the generic
        // type when subclasses extend this class. Of course it has some caveats: a second level of inheritance will
        // break this code, but this won't happen for our limited use case here. (I hope ;D).
        ParameterizedType superclass = (ParameterizedType)getClass().getGenericSuperclass();
        this.clazz = (Class<T>)superclass.getActualTypeArguments()[0];
    }

    @Override
    public T getById(Integer id) {
        return this.entityManager.find(this.clazz, id);
    }

    @Override
    public void delete(T entity) {
        this.entityManager.remove(entity);
    }

    @Override
    public void save(T entity) {
        this.entityManager.persist(entity);
    }

    @Override
    public List<T> list() {
        return this.entityManager.createQuery("select entity from " + this.clazz.getSimpleName() + " entity", this.clazz).getResultList();
    }

    protected TypedQuery<T> createNamedQuery(String name) {
        return this.entityManager.createNamedQuery(name, this.clazz);
    }
}
