package com.everest.employeePortal.services;

import com.everest.employeePortal.entities.Employee;
import com.everest.employeePortal.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;


    public Employee update(Employee newEmployee, Long id) {
        Optional<Employee> oldEmployee = employeeRepository.findById(id);
        if (oldEmployee.isEmpty()) {
            throw new EmployeeNotFoundException("No employee found with given ID : " + id);
        }
        Long permanent_address_id = employeeRepository.getPermanentAddressId(id);
        Long present_address_id = employeeRepository.getPresentAddressId(id);
        newEmployee.setId(id);
        newEmployee.getPermanentAddress().setId(permanent_address_id);
        newEmployee.getPresentAddress().setId(present_address_id);
        return employeeRepository.save(newEmployee);
    }
}
