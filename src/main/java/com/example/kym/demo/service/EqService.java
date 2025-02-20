package com.example.kym.demo.service;

import com.example.kym.demo.hotel_manager.Equipment;
import com.example.kym.demo.hotel_manager.EquipmentRep;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor

@Transactional
@Service
public class EqService {

private final EquipmentRep rep;

//CREATE

public Equipment createEQ(EquipmentDTO equipmentDTO) {
       return rep.save(
              Equipment.builder()
                      .name(equipmentDTO.name())
                      .amount(equipmentDTO.amount())
                      .build()
       );
}

//READ
    public List<Equipment> getAllEquipment() {
        return rep.findAll();
    }

    public Optional<Equipment> getEquipmentById(Long id) {
        return rep.findById(id);
    }

    public Optional<Equipment> getByName(String name) {
        return rep.findByName(name);
    }

//UPDATE

public Optional<Equipment> updateEquipment(String name, EquipmentDTO equipment) {
    return rep.findByName(name).map(
            equipment1 -> {
                equipment1.setName(equipment.name());
                equipment1.setAmount(equipment.amount());
                return rep.save(equipment1);
            }
    );
}

//DELETE

    public void deleteEquipment(String name) {
    rep.deleteByName(name);
    }

}
