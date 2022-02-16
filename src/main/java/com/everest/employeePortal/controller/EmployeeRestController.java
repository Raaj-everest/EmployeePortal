package com.everest.employeePortal.controller;

import com.everest.employeePortal.entities.Employee;
import com.everest.employeePortal.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/employees")
public class EmployeeRestController {

    private final EmployeeService employeeService;

    @GetMapping("")
    public Page<Employee> getAllEmployees(@RequestParam int page) {
        Sort defaultSort = Sort.by(Sort.Direction.ASC,"id");
        return employeeService.getAll(PageRequest.of(page, 1,defaultSort));

    }

    @GetMapping("/{id}")
    public Employee getEmployeeByID(@PathVariable Long id) {
        return employeeService.getByID(id);
    }
}
