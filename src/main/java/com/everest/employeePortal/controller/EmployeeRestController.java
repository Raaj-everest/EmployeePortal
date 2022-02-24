package com.everest.employeePortal.controller;

import com.everest.employeePortal.entities.Employee;
import com.everest.employeePortal.models.EmployeeResults;
import com.everest.employeePortal.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/employees")
public class EmployeeRestController {

    private final EmployeeService employeeService;

    @GetMapping("/search")
    public EmployeeResults searchEmployeesBy(@RequestParam String keyWord,
                                             @RequestParam(defaultValue = "1") Integer pageNo,
                                             @RequestParam(defaultValue = "1") Integer pageSize,
                                             @RequestParam(defaultValue = "id") String sortBy) {
        Sort sort = Sort.by(Sort.Direction.ASC, sortBy);
        if (pageNo < 1) {
            pageNo = 1;
        }
        return new EmployeeResults(employeeService.searchBy(keyWord, PageRequest.of(pageNo - 1, pageSize, sort)));
    }

    @PostMapping("")
    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee) {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.create(employee));
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @Valid @RequestBody Employee employee) {
        return employeeService.update(employee, id);
    }

    @GetMapping("")
    public EmployeeResults getAllEmployees(@RequestParam(defaultValue = "1") Integer pageNo,
                                           @RequestParam(defaultValue = "1") Integer pageSize,
                                           @RequestParam(defaultValue = "id") String sortBy) {
        Sort sort = Sort.by(Sort.Direction.ASC, sortBy);
        if (pageNo < 1) {
            pageNo = 1;
        }
        return new EmployeeResults(employeeService.getAll(PageRequest.of(pageNo - 1, pageSize, sort)));

    }

    @GetMapping("/{id}")
    public Employee getEmployeeByID(@PathVariable Long id) {
        return employeeService.getByID(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteEmployee(@PathVariable Long id) {
        employeeService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
