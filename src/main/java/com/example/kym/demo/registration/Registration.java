package com.example.kym.demo.registration;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "registration")
public class Registration extends AbstractPersistable<Long> {

    @ManyToOne
    @JoinColumn(name = "meeting_id")
    private Meeting meeting;


    @ManyToOne
    @JoinColumn(name = "alumni_id")
    private Alumni alumni;

    private LocalDateTime registrationTS;

    private boolean guaranteed;

}
