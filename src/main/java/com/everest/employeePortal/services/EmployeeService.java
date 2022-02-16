package com.everest.employeePortal.services;

import com.everest.employeePortal.entities.Employee;
import com.everest.employeePortal.exceptions.EmployeeNotFoundException;
import com.everest.employeePortal.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    public Employee getByID(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isEmpty()) {
            throw new EmployeeNotFoundException("No Employee found with given ID :" + id);
        }
        return employee.get();
    }
}
