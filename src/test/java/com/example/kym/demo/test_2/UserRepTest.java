package com.example.kym.demo.test_2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UserRepTest {

    private @Autowired UserRep rep;

    private User u;

    @BeforeEach
    void setup() {
        u = new User("Gay", "Sex");
    }

    @Test
    void ensureSavings() {
        var saved = rep.saveAndFlush(u);

        assertThat(saved).isNotNull().isSameAs(u);
    }

}
