package com.brainacademy.data.service.impl;

import com.brainacademy.data.domain.Pageable;
import com.brainacademy.data.model.Department;
import com.brainacademy.data.model.DepartmentEmployee;
import com.brainacademy.data.model.Employee;
import com.brainacademy.data.repository.DepartmentEmployeeRepository;
import com.brainacademy.data.repository.DepartmentRepository;
import com.brainacademy.data.repository.EmployeeRepository;
import com.brainacademy.data.service.EmployeeService;

import java.util.Date;
import java.util.concurrent.TimeUnit;


public class EmployeeServiceImpl
        implements EmployeeService {

    private static final long PERIOD_MS = TimeUnit.DAYS.toMillis(90);

    private DepartmentRepository departmentRepository;
    private EmployeeRepository employeeRepository;
    private DepartmentEmployeeRepository departmentEmployeeRepository;

    public EmployeeServiceImpl(DepartmentRepository departmentRepository,
                        EmployeeRepository employeeRepository,
                        DepartmentEmployeeRepository departmentEmployeeRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
        this.departmentEmployeeRepository = departmentEmployeeRepository;
    }

    @Override
    public Iterable<Department> getAvailableDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Iterable<Employee> searchEmployees(String departmentNumber, String employeeName, Pageable pageable) {
        if (employeeName != null && employeeName.length() > 0) {
            String[] parts = employeeName.split(" ");
            if (parts.length == 1) {
                return employeeRepository.findEmployees(departmentNumber, "%" + employeeName + "%", pageable);
            } else {
                return employeeRepository.findEmployees(departmentNumber,
                        "%" + parts[0] + "%",
                        "%" + parts[1] + "%",
                        pageable);
            }
        } else {
            return employeeRepository.findByDepartmentNumber(departmentNumber, pageable);
        }
    }

    @Override
    public Employee findById(Integer employeeId) {
        return employeeRepository.findOne(employeeId);
    }

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee save(Employee employee, Department department) {
        if (employee.getId() == null) {
            employeeRepository.save(employee);

            DepartmentEmployee departmentEmployee = new DepartmentEmployee();
            departmentEmployee.setFromDate(new Date());
            departmentEmployee.setToDate(new Date(System.currentTimeMillis() + PERIOD_MS));
            departmentEmployee.setDepartment(department);
            departmentEmployee.setEmployee(employee);
            departmentEmployeeRepository.save(departmentEmployee);

            return employee;
        } else {
            return employeeRepository.save(employee);
        }
    }

    @Override
    public void delete(Employee employee) {
        employeeRepository.delete(employee);
    }
}
