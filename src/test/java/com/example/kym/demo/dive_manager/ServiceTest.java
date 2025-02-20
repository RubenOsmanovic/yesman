package com.example.kym.demo.dive_manager;

import com.example.kym.demo.hotel_manager.*;
import com.example.kym.demo.hotel_manager.ServiceDive.ServiceDiving;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ServiceTest {

    @Mock
    private DiveRep diveRep;

    @Mock
    private BuddyRepo buddyRepo;

    @InjectMocks
    private ServiceDiving service;



    private Dive dive1;
    private Dive dive2;
    private Dive dive3;

    private Buddy buddy1;

    @BeforeEach
    void setUp() {
        DiverProfile dp = DiverProfile.builder()
                .avgConsuption(2.0)
                .duration(Duration.between(LocalTime.NOON, LocalTime.MIDNIGHT))
                .maxUsage(2.0)
                .build();
        Equipment equipment = Equipment.builder()
                .name("Gay Sex")
                .amount(10)
                .build();
        Equipment equipment2 = Equipment.builder()
                .name("Gay Sex")
                .amount(1)
                .build();
        Equipment equipment3 = Equipment.builder()
                .name("Gay Sex")
                .amount(4)
                .build();

        dive1 = Dive.builder()
                .date(LocalDateTime.now())
                .diverProfile(dp)
                .build();

        dive1.addEq(equipment);

        dive2 = Dive.builder()
                .date(LocalDateTime.now())
                .diverProfile(dp)
                .build();

        dive2.addEq(equipment);
        dive2.addEq(equipment2);

        dive3 = Dive.builder()
                .date(LocalDateTime.now())
                .diverProfile(dp)
                .build();

        dive3.addEq(equipment3);

        buddy1 = Buddy.builder()
                .info("asd")
                .bt(BuddyType.ASSISTANT_DIRECTOR)
                .name("Name")
                .build();

    }

    @Test
    void testGetDivingShit() {

        List<Dive> mockDives = List.of(dive1);

        when(diveRep.findAll()).thenReturn(mockDives);

        int totalEquipment = service.findAllEq();

        assertEquals(10, totalEquipment);

    }

    @Test
    void esnureFindBuddyByName() {

        List<Buddy> buddies = List.of(buddy1);
        when(buddyRepo.findAll()).thenReturn(buddies);

        List<Buddy> list = service.findBuddiesWithSpecificName("Name");

        assertEquals(buddies, list);
    }



}
