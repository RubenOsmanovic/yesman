package com.example.kym.demo.registration;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@Builder

@Embeddable
public class Address {

    @NotBlank
    private String streetNumber;

    @NotBlank
    private String zipCode;

    @NotBlank
    private String city;
}
