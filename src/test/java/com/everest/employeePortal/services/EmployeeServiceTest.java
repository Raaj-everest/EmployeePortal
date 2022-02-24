package com.everest.employeePortal.services;

import com.everest.employeePortal.entities.Employee;
import com.everest.employeePortal.exceptions.EmailIdAlreadyExistedException;
import com.everest.employeePortal.exceptions.EmployeeNotFoundException;
import com.everest.employeePortal.repositories.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class EmployeeServiceTest {

    private EmployeeRepository employeeRepository;
    private EmployeeService employeeService;


    @BeforeEach
    void setUp() {
        employeeRepository = mock(EmployeeRepository.class);
        employeeService = new EmployeeService(employeeRepository);
    }

    @Test
    void shouldThrowErrorIfUpdatingNonExistingEmployee() {
        Employee employee = new Employee();
        given(employeeRepository.findById(1L)).willReturn(Optional.empty());
        assertThrows((EmployeeNotFoundException.class), () -> {
            employeeService.update(employee, employee.id);
        });
    }

    @Test
    void shouldThrowErrorIfRetrieveNonExistingEmployee() {
        Employee employee = new Employee();
        given(employeeRepository.findByEverestEmailId(employee.getEverestEmailId())).willReturn(Optional.of(employee));
        assertThrows((EmailIdAlreadyExistedException.class), () -> {
            employeeService.create(employee);
        });
    }

    @Test
    void shouldThrowErrorIfCreatingEmployeeWithExistedEverestEmail() {
        Employee employee = new Employee();
        employee.setEverestEmailId("Rajesh@everest.engineering");
        given(employeeRepository.findByEverestEmailId(employee.getEverestEmailId())).willReturn(Optional.of(employee));
        assertThrows((EmailIdAlreadyExistedException.class), () -> {
            employeeService.create(employee);
        });
    }

    @Test
    void shouldThrowErrorIfDeletingEmployeeWhoIsNotExisted() {
        Employee employee = new Employee();
        employee.setId(2L);
        employee.setEverestEmailId("Rajesh@everest.engineering");
        given(employeeRepository.findById(employee.id)).willReturn(Optional.empty());
        assertThrows((EmployeeNotFoundException.class), () -> {
            employeeService.delete(employee.id);
        });
    }
}
