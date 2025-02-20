package com.example.kym.demo.exam;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class TeacherRepoTest {

    @Autowired
    private TeacherRepo repo;

    private Teacher teacher;

    @BeforeEach
    void setUp() {
        teacher = Teacher.builder()
                .firstName("John")
                .lastName("Doe")
                .build();

    }

    @Test
    void esnureSaving() {
        var saved = repo.save(teacher);

        assertThat(saved).isNotNull();
    }
}
