package com.everest.employeePortal.services;

import com.everest.employeePortal.entities.Employee;
import com.everest.employeePortal.exceptions.EmployeeNotFoundException;
import com.everest.employeePortal.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public Page<Employee> getAll(Pageable page) {
        return employeeRepository.findAll( page);
    }

    public Employee getByID(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isEmpty()) {
            throw new EmployeeNotFoundException("No Employee found with given ID :" + id);
        }
        return employee.get();
    }
}
