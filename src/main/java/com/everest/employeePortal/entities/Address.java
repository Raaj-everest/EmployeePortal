package com.everest.employeePortal.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "address_line_1", nullable = false)
    @NotEmpty(message = "The address line 1 must not empty")
    private String addressLine1;

    @Column(name = "address_line_2")
    private String addressLine2;

    @Column
    @NotEmpty(message = "The house number must not empty")
    private String city;

    @Column
    @NotEmpty(message = "The state must not empty")
    private String state;

    @Column
    @NotEmpty(message = "The country must not empty")
    private String country;

    @Column
    @NotNull(message = "Zip code should not be null")
    private Long zipcode;

}


