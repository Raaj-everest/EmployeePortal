package com.everest.employeePortal.controller;

import com.everest.employeePortal.entities.Employee;
import com.everest.employeePortal.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/employees")
public class EmployeeRestController {

    private final EmployeeService employeeService;

    @GetMapping("/search")
    public Page<Employee> searchEmployeesBy(@RequestParam String keyWord,
                                            @RequestParam(defaultValue = "0") Integer pageNo,
                                            @RequestParam(defaultValue = "1") Integer pageSize,
                                            @RequestParam(defaultValue = "id") String sortBy) {
        Sort sort = Sort.by(Sort.Direction.ASC, sortBy);
        return employeeService.searchBy(keyWord, PageRequest.of(pageNo, pageSize, sort));
    }

}
