package com.everest.employeePortal.services;

import com.everest.employeePortal.entities.Employee;
import com.everest.employeePortal.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public Page<Employee> searchBy(String data, Pageable pageable) {
        return employeeRepository.findHavingNameLike(data,pageable);
    }
}
