package com.brainacademy.data.repository.impl;

import com.brainacademy.data.domain.Page;
import com.brainacademy.data.domain.Pageable;
import com.brainacademy.data.model.Employee;
import com.brainacademy.data.repository.EmployeeRepository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class EmployeeRepositoryImpl
        extends AbstractRepository<Employee, Integer>
        implements EmployeeRepository {

    private static final String FIND_BY_DEPARTMENT_QUERY = "from Employee e "
            + "left join e.departmentEmployees as departmentEmployee "
            + "where departmentEmployee.primaryKey.department.number=:departmentNumber";

    private static final String FIND_BY_DEPARTMENT_COUNT_QUERY = "select count(e.id) "
            + FIND_BY_DEPARTMENT_QUERY;

    private static final String FIND_EMPLOYEE_QUERY = "from Employee e "
            + " left join e.departmentEmployees as departmentEmployee "
            + " where departmentEmployee.primaryKey.department.number=:departmentNumber "
            + "   and (e.firstName like :name or e.lastName like :name)";

    private static final String FIND_EMPLOYEE_COUNT_QUERY = "select count(e.id) "
            + FIND_EMPLOYEE_QUERY;

    private static final String FIND_EMPLOYEES_BY_NAME_QUERY = "from Employee e "
            + " left join e.departmentEmployees as departmentEmployee "
            + " where departmentEmployee.primaryKey.department.number=:departmentNumber "
            + "   and (e.firstName like :firstName and e.lastName like :lastName)";

    private static final String FIND_EMPLOYEES_BY_NAME_COUNT_QUERY = "select count(e.id) "
            + FIND_EMPLOYEES_BY_NAME_QUERY;

    public EmployeeRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Page<Employee> findByDepartmentNumber(String departmentNumber, Pageable pageable) {
        try (Session session = getSessionFactory().openSession()) {
            long total = (long) session.createQuery(FIND_BY_DEPARTMENT_COUNT_QUERY)
                    .setParameter("departmentNumber", departmentNumber)
                    .uniqueResult();

            List<Employee> employees = session.createQuery(FIND_BY_DEPARTMENT_QUERY, Employee.class)
                    .setParameter("departmentNumber", departmentNumber)
                    .setFirstResult(pageable.getPageNumber())
                    .setMaxResults(pageable.getPageSize())
                    .list();

            return new Page<>(employees, pageable, total);
        }
    }

    @Override
    public Page<Employee> findEmployees(String departmentNumber, String name, Pageable pageable) {
        try (Session session = getSessionFactory().openSession()) {
            long total = (long) session.createQuery(FIND_EMPLOYEE_COUNT_QUERY)
                    .setParameter("departmentNumber", departmentNumber)
                    .setParameter("name", name)
                    .uniqueResult();

            List<Employee> employees = session.createQuery(FIND_EMPLOYEE_QUERY, Employee.class)
                    .setParameter("departmentNumber", departmentNumber)
                    .setParameter("name", name)
                    .setFirstResult(pageable.getPageNumber())
                    .setMaxResults(pageable.getPageSize())
                    .list();

            return new Page<>(employees, pageable, total);
        }
    }

    @Override
    public Page<Employee> findEmployees(String departmentNumber, String firstName, String lastName, Pageable pageable) {
        try (Session session = getSessionFactory().openSession()) {
            long total = (long) session.createQuery(FIND_EMPLOYEE_COUNT_QUERY)
                    .setParameter("departmentNumber", departmentNumber)
                    .setParameter("firstName", firstName)
                    .setParameter("lastName", lastName)
                    .uniqueResult();

            List<Employee> employees = session.createQuery(FIND_EMPLOYEES_BY_NAME_QUERY, Employee.class)
                    .setParameter("departmentNumber", departmentNumber)
                    .setParameter("firstName", firstName)
                    .setParameter("lastName", lastName)
                    .setFirstResult(pageable.getPageNumber())
                    .setMaxResults(pageable.getPageSize())
                    .list();
            return new Page<>(employees, pageable, total);
        }
    }
}
