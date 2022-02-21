package com.everest.employeePortal.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "street")
    private String streetDetails;

    @Column(name = "house_number")
    private String houseNumber;

    @Column
    private String district;

    @Column
    private String city;

    @Column
    private String state;

    @Column
    private String country;

    @Column
    private Long zipCode;

}


