package com.everest.employeePortal.services;

import com.everest.employeePortal.entities.Address;
import com.everest.employeePortal.entities.Employee;
import com.everest.employeePortal.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public Employee create(Employee employee) {
        employeeRepository.save(employee);
        return employee;
    }

    public Employee update(Employee newEmployee, Long id) {
        Optional<Employee> oldEmployee = employeeRepository.findById(id);
        if (oldEmployee.isEmpty()) {
            throw new EmployeeNotFoundException("No employee found with given ID : " + id);
        }
        Long permanentAddressId = employeeRepository.getPermanentAddressId(id);
        Long presentAddressId = employeeRepository.getPresentAddressId(id);
        newEmployee.setId(id);
        newEmployee.getPermanentAddress().setId(permanentAddressId);
        newEmployee.getPresentAddress().setId(presentAddressId);
        return employeeRepository.save(newEmployee);
    }
}
