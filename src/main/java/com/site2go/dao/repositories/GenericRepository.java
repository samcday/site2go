package com.site2go.dao.repositories;

public interface GenericRepository<T> {
    public T getById(Integer id);
    public void delete(T entity);
    public void save(T entity);
}
