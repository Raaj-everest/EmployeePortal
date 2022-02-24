package com.everest.employeePortal.repositories;

import com.everest.employeePortal.entities.Address;
import com.everest.employeePortal.entities.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@DataJpaTest
class EmployeeRepositoryTest {

    @Autowired
    public EmployeeRepository employeeRepository;

    private final Employee raaj = new Employee();

    @BeforeEach
    void setUp() {
        raaj.setId(1L);
        raaj.setFirstName("Rajesh");
        raaj.setLastName("Yadav");
        raaj.setEverestEmailId("Rajesh@everest.engineering");
        raaj.setPassword("ffffffff");
        raaj.setPersonalEmailId("rajesh@gmail.com");
        raaj.setDob(LocalDate.of(1971, 2, 2));
        raaj.setDoj(LocalDate.of(2022, 2, 16));
        raaj.setDesignation("TEAM LEAD");
        raaj.setExperienceInYears(10);
        raaj.setBio("NEN NEEKU CHEPPANU");
        Address address = new Address();
        address.setAddressLine1("first");
        address.setState("ANDHRA");
        address.setCity("ATP");
        address.setCountry("India");
        address.setZipcode(123456L);
        raaj.setPermanentAddress(address);
        address.setAddressLine2("present");
        raaj.setPresentAddress(address);
        employeeRepository.save(raaj);
    }

    @Test
    public void shouldSearchWithEmployeeName(){
        assertEquals(employeeRepository.findHavingNameLike("raj", Pageable.unpaged()), new PageImpl<>(Collections.singletonList(raaj)));
    }
    @Test
    public void shouldNotGiveEmployeesIfNameNotMatches(){
        assertNotEquals(employeeRepository.findHavingNameLike("Bakra", Pageable.unpaged()), new PageImpl<>(Collections.singletonList(raaj)));
    }
    @Test
    public void shouldFindEmployeeWithEverestEmailId(){
        assertEquals(employeeRepository.findByEverestEmailId("Rajesh@everest.engineering"), Optional.of(raaj));
    }
    @Test
    public void shouldNotFindEmployeeWithWrongEverestEmailId(){
        assertNotEquals(employeeRepository.findByEverestEmailId("Rah@everest.engineering"), Optional.of(raaj));
    }
    @Test
    void shouldDeletingEmployeeWhoIsExisted() {
        employeeRepository.delete(raaj);
        assertTrue(employeeRepository.findById(1L).isEmpty());
    }
}