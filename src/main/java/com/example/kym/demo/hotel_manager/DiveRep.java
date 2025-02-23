package com.example.kym.demo.hotel_manager;

import com.example.kym.demo.hotel_manager.dto.DiveDTO;
import com.example.kym.demo.hotel_manager.dto.DiveProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface DiveRep extends JpaRepository<Dive, Long> {

    Optional<Dive> findByDate(LocalDateTime date);

    List<DiveProjection> findAllAsProjection();

}
