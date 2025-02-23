package com.example.kym.demo.hotel_manager.dto;

import com.example.kym.demo.hotel_manager.DiverProfile;

import java.time.Duration;

public record DiveProfileDTO(
    Duration duration,
    Double maxUsage,
    Double avgConsumption
) {

    public DiveProfileDTO(DiverProfile diverProfile) {
        this(diverProfile.getDuration(), diverProfile.getMaxUsage(), diverProfile.getAvgConsuption());
    }
}
