package com.everest.employeePortal.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "street")
    @NotEmpty(message = "The street name must not empty")
    private String streetDetails;

    @Column(name = "house_number")
    @NotEmpty(message = "The house number must not empty")
    private String houseNumber;

    @Column
    @NotEmpty(message = "The district must not empty")
    private String district;

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
    private Long zipCode;

}


