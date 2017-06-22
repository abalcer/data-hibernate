package com.brainacademy.data.repository;

import java.io.Serializable;

public interface CrudRepository<T, ID extends Serializable> {

    Iterable<T> findAll();

    T findOne(ID id);

    T save(T entity);

    void delete(T entity);
}
