package com.example.kym.demo.hotel_manager;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.time.Duration;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder

@Embeddable
public class DiverProfile {

    @Column(nullable = false)
    private Duration duration;

    @Column
    private Double maxUsage;

    @Column
    private Double avgConsuption;

}
