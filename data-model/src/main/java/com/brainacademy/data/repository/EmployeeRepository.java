package com.brainacademy.data.repository;


import com.brainacademy.data.domain.Page;
import com.brainacademy.data.domain.Pageable;
import com.brainacademy.data.model.Employee;


public interface EmployeeRepository
        extends CrudRepository<Employee, Integer> {

    Page<Employee> findByDepartmentNumber(String departmentNumber, Pageable pageable);

    Page<Employee> findEmployees(String departmentNumber, String name, Pageable pageable);

    Page<Employee> findEmployees(String departmentNumber, String firstName, String lastName,
                                 Pageable pageable);
}
