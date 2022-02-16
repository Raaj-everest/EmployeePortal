package com.everest.employeePortal.services;

import com.everest.employeePortal.entities.Employee;
import com.everest.employeePortal.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public List<Employee> searchBy(String data) {
        List<Employee> employees = new ArrayList<>();
        employees.addAll(employeeRepository.findByFirstNameLike(data));
        employees.addAll(employeeRepository.findByLastNameLike(data));
        return employees;
    }
}
