package com.example.kym.demo.hotel_manager.dto;

import com.example.kym.demo.hotel_manager.Equipment;

public record EquipmentDTO(
    String name,
    Integer amount
) {

    public EquipmentDTO(Equipment equipment) {
        this(equipment.getName(), equipment.getAmount());
    }
}
