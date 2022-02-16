package com.everest.employeePortal.controller;

import com.everest.employeePortal.entities.Employee;
import com.everest.employeePortal.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/employees")
public class EmployeeRestController {

    private final EmployeeService employeeService;

    @PostMapping("")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.create(employee);
    }
}
