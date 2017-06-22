package com.brainacademy.swing.config;


import com.brainacademy.data.model.Department;
import com.brainacademy.data.model.DepartmentEmployee;
import com.brainacademy.data.model.Employee;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DatabaseConfig {
//    Alter table titles drop FOREIGN KEY titles_ibfk_1;
//    Alter table salaries drop FOREIGN KEY salaries_ibfk_1;
//    Alter table dept_manager drop FOREIGN KEY dept_manager_ibfk_1;
//    Alter table dept_emp drop FOREIGN KEY dept_emp_ibfk_1;
//
//    ALTER TABLE employees MODIFY COLUMN emp_no INT auto_increment;

    public static SessionFactory setup() {
        Configuration configuration = new Configuration();
        configuration.setProperties(hibernateProperties());

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();

        Metadata metadata = new MetadataSources(serviceRegistry)
                .addAnnotatedClass(Department.class)
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(DepartmentEmployee.class)
                .getMetadataBuilder()
                .build();

        return metadata.getSessionFactoryBuilder().build();
    }


    private static Properties hibernateProperties() {
        try {
            Properties properties = new Properties();
            InputStream inputStream = DatabaseConfig.class.getClassLoader().getResourceAsStream("hibernate.properties");
            properties.load(inputStream);
            return properties;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        DatabaseConfig.setup();
    }
}
