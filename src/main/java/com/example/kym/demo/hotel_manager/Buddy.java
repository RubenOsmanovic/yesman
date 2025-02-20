package com.example.kym.demo.hotel_manager;

import com.example.kym.demo.converters.BuddyTypeConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "buddies")
public class Buddy extends AbstractPersistable<Long> {

    @Column(nullable = false, length = 64)
    private @NotBlank String name;

    @Column(length = 255)
    private String info;

    @Convert(converter = BuddyTypeConverter.class)
    @Column
    private BuddyType bt;

}
