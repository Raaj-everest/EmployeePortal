package com.everest.employeePortal.controller;

import com.everest.employeePortal.entities.Employee;
import com.everest.employeePortal.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/employees")
public class EmployeeRestController {

    private final EmployeeService employeeService;

    @GetMapping("/search")
    public List<Employee> searchEmployeesBy(@RequestParam String keyWord) {
        return employeeService.searchBy(keyWord);
    }

}
