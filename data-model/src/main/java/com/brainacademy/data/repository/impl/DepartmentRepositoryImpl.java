package com.brainacademy.data.repository.impl;

import com.brainacademy.data.model.Department;
import com.brainacademy.data.repository.DepartmentRepository;

import org.hibernate.SessionFactory;

public class DepartmentRepositoryImpl
        extends AbstractRepository<Department, String>
        implements DepartmentRepository {

    public DepartmentRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
