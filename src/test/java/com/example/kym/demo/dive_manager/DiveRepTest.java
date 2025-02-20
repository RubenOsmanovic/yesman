package com.example.kym.demo.dive_manager;

import com.example.kym.demo.hotel_manager.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalUnit;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class DiveRepTest {

    private @Autowired DiveRep rep;
    private @Autowired EquipmentRep eqRep;
    private Dive d;
    private Equipment equipment;
    @BeforeEach
    void setup() {
        DiverProfile dp = DiverProfile.builder()
                .avgConsuption(2.0)
                .duration(Duration.between(LocalTime.NOON, LocalTime.MIDNIGHT))
                .maxUsage(2.0)
                .build();
         equipment = Equipment.builder()
                 .name("Gay Sex")
                 .amount(10)
                 .build();
        d = Dive.builder()
                .date(LocalDateTime.now())
                .diverProfile(dp)
                .build();

        d.addEq(equipment);

    }

    @Test
    void esnrueSavingWorks() {
        eqRep.save(equipment);
        var saved = rep.saveAndFlush(d);

        assertThat(saved).isNotNull().isSameAs(d);

    }
}
