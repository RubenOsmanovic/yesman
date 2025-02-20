package com.example.kym.demo.test_2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class PizzaRepTest {

    private @Autowired PizzaRep rep;

    private Pizza p;

    @BeforeEach
    void setup() {

        p = Pizza.builder()
                .name("Chonky Ronky")
                .pt(PizzaType.XL)
                .build();

    }

    @Test
    public void ensureSavingWorks() {

        var saved = rep.saveAndFlush(p);

        assertThat(saved).isNotNull().isSameAs(p);

    }

}
