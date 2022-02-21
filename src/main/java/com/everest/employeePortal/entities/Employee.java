package com.everest.employeePortal.entities;

import com.everest.employeePortal.entities.enums.Designation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "date_of_join", nullable = false)
    private LocalDate dateOfJoin;

    @Column(nullable = false)
    private Designation designation;

    @Column(name = "company_mail", nullable = false)
    private String everestEmail;

    @Column(name = "personal_mail")
    private String personalEmail;

    @Column
    private String bio;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "present_address_id")
    private Address presentAddress;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "permanent_address_id")
    private Address permanentAddress;

}
