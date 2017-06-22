package com.brainacademy.swing;


import com.brainacademy.data.model.Department;
import com.brainacademy.data.repository.DepartmentEmployeeRepository;
import com.brainacademy.data.repository.DepartmentRepository;
import com.brainacademy.data.repository.EmployeeRepository;
import com.brainacademy.data.repository.impl.DepartmentEmployeeRepositoryImpl;
import com.brainacademy.data.repository.impl.DepartmentRepositoryImpl;
import com.brainacademy.data.repository.impl.EmployeeRepositoryImpl;
import com.brainacademy.data.service.DepartmentService;
import com.brainacademy.data.service.EmployeeService;
import com.brainacademy.data.service.impl.DepartmentServiceImpl;
import com.brainacademy.data.service.impl.EmployeeServiceImpl;
import com.brainacademy.swing.config.DatabaseConfig;
import com.brainacademy.swing.ui.MainFrame;
import com.brainacademy.swing.ui.forms.EmployeeListForm;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

import javax.swing.SwingUtilities;

public class Application {

    private SessionFactory sessionFactory;

    private EmployeeListForm employeeListForm;
    private MainFrame mainFrame;

    private DepartmentService departmentService;
    private EmployeeService employeeService;

    private DepartmentEmployeeRepository departmentEmployeeRepository;
    private DepartmentRepository departmentRepository;
    private EmployeeRepository employeeRepository;

    public Application() {
        sessionFactory = DatabaseConfig.setup();

        departmentEmployeeRepository = new DepartmentEmployeeRepositoryImpl(sessionFactory);
        departmentRepository = new DepartmentRepositoryImpl(sessionFactory);
        employeeRepository = new EmployeeRepositoryImpl(sessionFactory);

        departmentService = new DepartmentServiceImpl(departmentRepository);
        employeeService = new EmployeeServiceImpl(departmentRepository, employeeRepository, departmentEmployeeRepository);

        try (Session session = sessionFactory.openSession()) {
            List<Department> list = session.createQuery("from Department", Department.class).list();
            System.out.println(list);
        }

        this.employeeListForm = new EmployeeListForm(employeeService, departmentService);
        this.mainFrame = new MainFrame(employeeListForm);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Application application = new Application();
            application.mainFrame.showForm();
        });
    }
}
