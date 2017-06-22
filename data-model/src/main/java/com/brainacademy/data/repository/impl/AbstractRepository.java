package com.brainacademy.data.repository.impl;

import com.brainacademy.data.repository.CrudRepository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

public class AbstractRepository<T, ID extends Serializable>
        implements CrudRepository<T, ID> {

    private SessionFactory sessionFactory;

    public AbstractRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Iterable<T> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from " + getGenericName(),
                    getGenericСlass())
                    .list();
        }
    }

    @Override
    public T findOne(ID id) {
        try (Session session = sessionFactory.openSession()) {
            return session.byId(getGenericСlass()).load(id);
        }
    }

    @Override
    public T save(T entity) {
        try (Session session = sessionFactory.openSession()) {
            session.save(entity);
            return entity;
        }
    }

    @Override
    public void delete(T entity) {
        try (Session session = sessionFactory.openSession()) {
            session.delete(entity);
        }
    }

    SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    private String getGenericName() {
        return getGenericСlass().getSimpleName();
    }

    @SuppressWarnings("unchecked")
    private Class<T> getGenericСlass() {
        return (Class<T>)
                ((ParameterizedType) getClass()
                        .getGenericSuperclass())
                        .getActualTypeArguments()[0];
    }
}
