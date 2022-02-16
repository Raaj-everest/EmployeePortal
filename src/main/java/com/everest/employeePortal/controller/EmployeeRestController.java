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
    public Page<Employee> getAllEmployees( @RequestParam(defaultValue = "0") Integer pageNo,
                                           @RequestParam(defaultValue = "1") Integer pageSize,
                                           @RequestParam(defaultValue = "id") String sortBy) {
        Sort sort = Sort.by(Sort.Direction.ASC,sortBy);
        return employeeService.getAll(PageRequest.of(pageNo, pageSize,sort));

    }

    @GetMapping("/{id}")
    public Employee getEmployeeByID(@PathVariable Long id) {
        return employeeService.getByID(id);
    }
}
