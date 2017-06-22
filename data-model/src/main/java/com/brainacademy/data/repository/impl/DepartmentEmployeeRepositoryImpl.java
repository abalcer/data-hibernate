package com.brainacademy.data.repository.impl;

import com.brainacademy.data.model.DepartmentEmployee;
import com.brainacademy.data.model.DepartmentEmployeeId;
import com.brainacademy.data.repository.DepartmentEmployeeRepository;

import org.hibernate.SessionFactory;

public class DepartmentEmployeeRepositoryImpl
        extends AbstractRepository<DepartmentEmployee, DepartmentEmployeeId>
        implements DepartmentEmployeeRepository {

    public DepartmentEmployeeRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
