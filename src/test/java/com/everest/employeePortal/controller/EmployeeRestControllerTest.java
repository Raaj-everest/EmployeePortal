package com.everest.employeePortal.controller;

import com.everest.employeePortal.entities.Address;
import com.everest.employeePortal.entities.Employee;
import com.everest.employeePortal.services.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class EmployeeRestControllerTest {

    private static Employee raaj = new Employee();
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private EmployeeService employeeService;

    @BeforeAll
    static void setUp() {
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
    }

    @Test
    void shouldGiveBadRequestStatusIfEmployeeEmailIsNotValid() throws Exception {
        raaj.setEverestEmailId("raaj.@ever.engineering"); //creating employee with InValid Email
        given(employeeService.create(raaj)).willReturn(raaj);
        mockMvc.perform(post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(raaj)))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException))
                .andExpect(jsonPath("$.message[0]", is("everestEmailId : look again at the mail provided")));

    }

    @Test
    void shouldGiveCreatedStatusIfEmployeeDetailsAreValid() throws Exception {
        given(employeeService.create(raaj)).willReturn(raaj);
        mockMvc.perform(post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(raaj)))
                .andExpect(status().isCreated());
    }


    @Test
    void shouldUpdateEmployeeIfEmployeeDetailsAreValid() throws Exception {
        Employee Raaj = new Employee();
        raaj.setId(1L);
        raaj.setFirstName("Kiran");
        given(employeeService.update(Raaj, Raaj.id)).willReturn(Raaj);
        mockMvc.perform(put("/api/employees/{id}", raaj.id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(raaj)))
                .andExpect(status().isOk());
    }

    @Test
    void shouldGetAllEmployees() throws Exception {
        List<Employee> employees = new ArrayList<>();
        Employee rajesh = new Employee();
        rajesh.setId(1L);
        rajesh.setFirstName("rajesh");
        Employee Kiran = new Employee();
        Kiran.setId(2L);
        Kiran.setFirstName("Kiran");
        employees.add(rajesh);
        employees.add(Kiran);
        employees.add(raaj);
        Page<Employee> page = new PageImpl<>(employees);
        given(employeeService.getAll(PageRequest.of(0, 1, Sort.by(Sort.Direction.ASC, "id")))).willReturn(page);
        mockMvc.perform(get("/api/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements", is(employees.size())));
    }

    @Test
    void shouldDeleteEmployee() throws Exception {
        raaj.setId(1L);
        given(employeeService.getByID(raaj.id)).willReturn(raaj);
        this.mockMvc.perform(delete("/api/employees/{id}", raaj.getId()))
                .andExpect(status().isOk());
    }
}