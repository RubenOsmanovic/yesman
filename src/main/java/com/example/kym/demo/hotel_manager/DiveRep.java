package com.example.kym.demo.hotel_manager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface DiveRep extends JpaRepository<Dive, Long> {

    Optional<Dive> findByDate(LocalDateTime date);


}
