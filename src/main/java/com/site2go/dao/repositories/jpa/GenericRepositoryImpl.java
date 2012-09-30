package com.site2go.dao.repositories.jpa;

import com.site2go.dao.repositories.GenericRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;

public class GenericRepositoryImpl<T> implements GenericRepository<T> {
    @PersistenceContext
    private EntityManager entityManager;

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
}