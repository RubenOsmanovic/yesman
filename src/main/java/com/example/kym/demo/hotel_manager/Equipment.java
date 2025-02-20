package com.example.kym.demo.hotel_manager;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder


@Entity
@Table(name = "equipment")
public class Equipment extends AbstractPersistable<Long> {

    @Column(nullable = false, length = 64)
    private @NotBlank String name;

    @Column
    private Integer amount;

    @ManyToOne
    @JoinColumn(name = "dive_id")
    private Dive dive;
}
