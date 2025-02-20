package com.example.kym.demo.test_2;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Embeddable
public class Address {

    @Column(length = 4, nullable = false)
    private Integer zip;

    @Column(length = 255, nullable = false)
    private String streetName;

    @Column
    private Integer houseNr;
}
