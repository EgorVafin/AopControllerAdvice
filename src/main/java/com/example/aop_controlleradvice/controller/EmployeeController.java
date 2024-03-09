package com.example.aop_controlleradvice.controller;

import com.example.aop_controlleradvice.exception.ResourceNotFoundException;
import com.example.aop_controlleradvice.exception.ResourceProcessingException;
import com.example.aop_controlleradvice.model.Employee;
import com.example.aop_controlleradvice.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    //Happy path, an employee is returned as response
    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public Employee getEmployee() throws ResourceNotFoundException, ResourceProcessingException {
        try {
            Employee employee = employeeService.getEmployee();

            if (employee == null) {
                throw new ResourceNotFoundException("Employee not found");
            }
            return employee;
        } catch (ResourceProcessingException e) {
            throw new ResourceProcessingException("Internal Server Exception while getting exception");
        }
    }

//    no employee found so ResourceNotFoundException is thrown
    @RequestMapping(value = "/employee2", method = RequestMethod.GET)
    public Employee getEmployee2() throws ResourceNotFoundException, ResourceProcessingException {
        try {
            Employee employee = employeeService.getEmployeeNull();
            if (employee == null) {
                throw new ResourceNotFoundException("Employee not found");
            }

            return employee;
        } catch (ResourceProcessingException e) {
            throw new ResourceNotFoundException("Internal Server Exception while getting exception");
        }
    }

    //Some exception is thrown by service layer
    @RequestMapping(value = "/employee3", method = RequestMethod.GET)
    public Employee getEmployee3() throws ResourceNotFoundException, ResourceProcessingException {
        try {
            Employee exception = employeeService.getEmployeeException();
            if (exception == null) {
                throw new ResourceNotFoundException("Employee not found");
            }
            return exception;
        } catch (ResourceProcessingException e) {
            throw new ResourceProcessingException("Internal Server Exception while getting exception");
        }
    }


}
