package com.brainacademy.data.repository;

import com.brainacademy.data.model.DepartmentEmployee;
import com.brainacademy.data.model.DepartmentEmployeeId;

public interface DepartmentEmployeeRepository
        extends CrudRepository<DepartmentEmployee, DepartmentEmployeeId> {
}
