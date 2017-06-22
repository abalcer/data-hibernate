package com.brainacademy.data.service.impl;

import com.brainacademy.data.model.Department;
import com.brainacademy.data.repository.DepartmentRepository;
import com.brainacademy.data.service.DepartmentService;

public class DepartmentServiceImpl
        implements DepartmentService {

    private DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department getByNumber(String departmentNumber) {
        return departmentRepository.findOne(departmentNumber);
    }

    @Override
    public Department save(Department department) {
        return departmentRepository.save(department);
    }
}
