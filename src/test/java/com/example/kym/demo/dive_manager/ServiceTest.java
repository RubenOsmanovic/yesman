package com.example.kym.demo.dive_manager;

import com.example.kym.demo.hotel_manager.*;
import com.example.kym.demo.hotel_manager.ServiceDive.ServiceDiving;
import com.example.kym.demo.hotel_manager.dto.DiveProfileDTO;
import com.example.kym.demo.hotel_manager.dto.DiveProjection;
import com.example.kym.demo.hotel_manager.dto.EquipmentDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
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

    private DiveProjection mockProjection1;
    private DiveProjection mockProjection2;

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

        mockProjection1 = new DiveProjection() {
            @Override
            public LocalDateTime getDate() {
                return LocalDateTime.of(2024, 2, 10, 14, 0);
            }

            @Override
            public DiveProfileDTO getDiveProfile() {
                return new DiveProfileDTO(new DiverProfile(Duration.ofHours(1),2.0 , 3.0));
            }

            @Override
            public List<EquipmentDTO> getEquipments() {
                return List.of(new EquipmentDTO("Tank", 1));
            }
        };

        mockProjection2 = new DiveProjection() {
            @Override
            public LocalDateTime getDate() {
                return LocalDateTime.of(2024, 2, 9, 10, 0);
            }

            @Override
            public DiveProfileDTO getDiveProfile() {
                return new DiveProfileDTO(new DiverProfile(Duration.ofMinutes(45),2.5, 3.5));
            }

            @Override
            public List<EquipmentDTO> getEquipments() {
                return List.of(new EquipmentDTO("Regulator", 2));
            }
        };
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

    @Test
    void testFindByDateWithProjections() {
        when(diveRep.findAllAsProjection()).thenReturn(List.of(mockProjection1, mockProjection2));

        List<DiveProjection> result = service.findByDateProjection(LocalDate.of(2024, 2, 9));

        assertEquals(2, result.size());
        assertEquals(mockProjection1.getDate(), result.get(0).getDate());
        assertEquals(mockProjection2.getDate(), result.get(1).getDate());
    }

    @Test
    void assertThatProperValuesAreReturendWhenGroupingBy() {
        when(diveRep.findAllAsProjection()).thenReturn(List.of(mockProjection1, mockProjection2));

        Map<String, Long> map = service.findUsageOfEqInDives();

        assertEquals(2, map.size());
        assertThat(map.get("Tank")).isEqualTo(1L);
    }


}
