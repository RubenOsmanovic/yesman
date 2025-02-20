package com.example.kym.demo.registration;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Table(name = "alumni")
@Entity
public class Alumni extends AbstractPersistable<Long> {

    @Column(unique = true, nullable = false)
    private String email;

    private String firstName;

    private String lastName;



}
