package com.example.kym.demo.exam;

import jakarta.persistence.Table;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class StudentRepoTest {

    @Autowired
    private StudentRepo studentRepo;

    private Student student;

    @BeforeEach
    void setUp() {
        student = Student.builder()
                .studentId("Niger")
                .firstName("N")
                .lastName("C")
                .birthDate("12-2-22")
                .build();
    }

    @Test
    void esnureSaving() {
        var saved = studentRepo.save(student);

        assertThat(saved).isNotNull();
    }
}
