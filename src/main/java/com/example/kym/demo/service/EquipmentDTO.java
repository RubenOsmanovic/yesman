package com.example.kym.demo.service;

import com.example.kym.demo.hotel_manager.Equipment;

public record EquipmentDTO(
        Long id,
        String name,
        Integer amount
) {
    public EquipmentDTO(Equipment equipment) {
        this(equipment.getId(), equipment.getName(), equipment.getAmount());
    }
}
