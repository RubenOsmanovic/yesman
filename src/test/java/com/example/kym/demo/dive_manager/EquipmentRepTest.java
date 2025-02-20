package com.example.kym.demo.dive_manager;

import com.example.kym.demo.hotel_manager.Equipment;
import com.example.kym.demo.hotel_manager.EquipmentRep;
import lombok.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class EquipmentRepTest {

    private @Autowired EquipmentRep equipmentRep;

    private Equipment eq1;
    @BeforeEach
    void setup() {
        eq1 = Equipment.builder()
                .name("Gay Sex")
                .amount(10)
                .build();
    }

    @Test
    void esnureSavingWorks() {

        var saved = equipmentRep.saveAndFlush(eq1);

        assertThat(saved).isNotNull().isSameAs(eq1);
        assertThat(saved.getId()).isNotNull();
    }

}
