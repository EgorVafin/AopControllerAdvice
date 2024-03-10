package com.example.aop_controlleradvice.controller;

import com.example.aop_controlleradvice.exception.ResourceNotFoundException;
import com.example.aop_controlleradvice.exception.ResourceProcessingException;
import com.example.aop_controlleradvice.model.Employee;
import com.example.aop_controlleradvice.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

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

//    @PostMapping("/order/create")
//    public String create(@Valid @ModelAttribute(name = "order")
//                         OrderCreateFormRequest orderCreateFormRequest,
//                         BindingResult bindingResult,
//                         Model model) {
//
//        if (bindingResult.hasErrors()) {
//            List<Customer> customers = customerRepository.findAll();
//            model.addAttribute("customers", customers);
//
//            model.addAttribute("order", orderCreateFormRequest);
//            return "orders/order_create_form";
//        }
//
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        orderCreateFormRequest.setUser(user);
//
//        orderService.save(orderCreateFormRequest);
//        return "redirect:/order";
//    }


// ---------------------------------------------
//    @RestController
//    public class UserController {
//
//        @PostMapping("/users")
//        ResponseEntity<String> addUser(@Valid @RequestBody User user) {
//            // persisting the user
//            return ResponseEntity.ok("User is valid");
//        }
//
//    }


}
