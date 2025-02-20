package com.example.kym.demo.test_2;

import com.example.kym.demo.converters.PizzaTypeConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder

@Entity
@Table(name="pizza")
public class Pizza extends AbstractPersistable<Long> {

    @Column(nullable = false, length = 64)
    private String name;

    @Column
    @Convert(converter = PizzaTypeConverter.class)
    private @NotNull PizzaType pt;

    @ManyToOne
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;



}
