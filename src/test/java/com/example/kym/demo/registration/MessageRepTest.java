package com.example.kym.demo.registration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class MessageRepTest {

    @Autowired
    private MeetingRep rep;

    private Meeting meeting1;

    private Address address;
    @BeforeEach
    public void setUp() {

        address = Address.builder()
                .city("Yes")
                .streetNumber("21")
                .zipCode("as")
                .build();

        meeting1 = Meeting.builder()
                .address(address)
                .code("ads")
                .date(LocalDate.now())
                .title("asd")
                .room("ads")
                .capacity(10)
                .description("ads")
                .endTime(LocalTime.now())
                .startTime(LocalTime.now())
                .build();


    }

    @Test
    void esnuteRqsaveq() {

        var saved = rep.save(meeting1);

        assertThat(saved).isNotNull();
    }

}
