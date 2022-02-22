package com.everest.employeePortal.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
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

    @Column(name = "first_name")
    @NotBlank(message = "The first Name field must not blank")
    @Size(min = 3)
    private String firstName;

    @Column(name = "last_name")
    @NotBlank(message = "The last Name field must not blank")
    private String lastName;

    @Column(name = "company_mail", nullable = false,unique = true)
    @Email(regexp = ".+@everest.engineering", message = "look again at the mail provided")
    private String everestEmailId;

    @Column(name = "password", nullable = false)
    @NotEmpty(message = "Provide a password for your mail")
    private String password;

    @Column(name = "personal_mail")
    @Email
    private String personalEmailId;

    @Column(name = "date_of_birth")
    @Past
    private LocalDate dob;

    @Column(name = "date_of_join", nullable = false)
    private LocalDate doj;

    @Column
    @NotBlank(message = "your role must be specified")
    private String designation;

    @Column(name = "Experience_in_years")
    @NotNull(message = "your role must be specified")
    private int experienceInYears;

    @Column
    @Size(min = 5, max = 100, message = "Bio should contain a minimum info about you")
    private String bio;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "present_address_id")
    private Address presentAddress;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "permanent_address_id")
    private Address permanentAddress;

}
