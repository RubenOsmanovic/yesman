package com.example.kym.demo.registration;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "meeting")
public class Meeting extends AbstractPersistable<Long> {

    @Column(unique = true, nullable = false)
    private String code;

    @Column(nullable = false, unique = true)
    private String title;

    private String description;

    @Embedded
    private Address address;

    private String room;

    private LocalDate date;

    private LocalTime startTime;

    private LocalTime endTime;

    private int capacity;
}
