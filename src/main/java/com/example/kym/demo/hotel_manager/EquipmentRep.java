package com.example.kym.demo.hotel_manager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EquipmentRep extends JpaRepository<Equipment, Long> {
    Optional<Equipment> findByName(String name);

    void deleteByName(String name);

    boolean existsByName(String name);
}
