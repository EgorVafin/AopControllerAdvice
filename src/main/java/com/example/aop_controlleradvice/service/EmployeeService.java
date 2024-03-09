package com.example.aop_controlleradvice.service;

import com.example.aop_controlleradvice.exception.ResourceNotFoundException;
import com.example.aop_controlleradvice.exception.ResourceProcessingException;
import com.example.aop_controlleradvice.model.Employee;
import lombok.*;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    public EmployeeService() {
    }

    public Employee getEmployee() throws ResourceProcessingException {
        Employee employee = new Employee();
        employee.setId(1);
        employee.setName("Anna");
        employee.setEmail("annaKoroleva@gmail.com");
        employee.setDepartment("HR manager");
        employee.setSalary(5700);

        return employee;
    }

    public Employee getEmployeeNull() throws ResourceProcessingException {

        return null;
    }


    public Employee getEmployeeException() throws ResourceProcessingException {

        throw  new ResourceProcessingException();
    }
}
