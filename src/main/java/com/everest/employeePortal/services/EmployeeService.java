package com.everest.employeePortal.services;

import com.everest.employeePortal.entities.Employee;
import com.everest.employeePortal.exceptions.EmailIdAlreadyExistedException;
import com.everest.employeePortal.exceptions.EmployeeNotFoundException;
import com.everest.employeePortal.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public Page<Employee> searchBy(String data, Pageable pageable) {
        return employeeRepository.findHavingNameLike(data, pageable);
    }

    public Employee create(Employee employee) {
        Optional<Employee> check = employeeRepository.findByEverestEmailId(employee.getEverestEmailId());
        if(check.isPresent()){
            throw new EmailIdAlreadyExistedException("Email id is already given to some other employee");
        }
        return employeeRepository.save(employee);
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

    public Page<Employee> getAll(Pageable page) {
        return employeeRepository.findAll(page);
    }

    public Employee getByID(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isEmpty()) {
            throw new EmployeeNotFoundException("No Employee found with given ID :" + id);
        }
        return employee.get();
    }

    public void delete(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isEmpty()) {
            throw new EmployeeNotFoundException("NO Employee Found with given Id :" + id + "perhaps he is not exists ");
        }
        employeeRepository.delete(employee.get());
    }
}

