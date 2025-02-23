package com.example.kym.demo.hotel_manager.dto;

import java.time.LocalDateTime;
import java.util.List;

public interface DiveProjection {
    LocalDateTime getDate();
    DiveProfileDTO getDiveProfile();
    List<EquipmentDTO> getEquipments();
}
