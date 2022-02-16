package com.everest.employeePortal.entities;

import com.everest.employeePortal.entities.enums.Designation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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

    @Column(name = "date_of_birth")
    @Past
    private LocalDate dateOfBirth;

    @Column(name = "date_of_join", nullable = false)
    @FutureOrPresent
    private LocalDate dateOfJoin;

    @NotNull(message = "your role must be specified")
    private Designation designation;

    @Column(name = "company_mail", nullable = false)
    @Email(message = "look again at the mail provided")
    private String everestEmail;

    @Column(name = "personal_mail")
    @Email
    private String personalEmail;

    @Column
    @Size(min = 10,max = 100,message = "Bio should contain a minimum info about you")
    private String bio;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "present_address_id")
    private Address presentAddress;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "permanent_address_id")
    private Address permanentAddress;

}
