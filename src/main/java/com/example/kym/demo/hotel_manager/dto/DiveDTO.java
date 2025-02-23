package com.example.kym.demo.hotel_manager.dto;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public record DiveDTO(
        LocalDateTime date,
        Duration duration,
        Double maxUsage,
        Double avgConsumption,
        List<EquipmentDTO> eqList
) {


}
