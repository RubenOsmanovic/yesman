package com.example.kym.demo.dive_manager;

import com.example.kym.demo.hotel_manager.Buddy;
import com.example.kym.demo.hotel_manager.BuddyRepo;
import com.example.kym.demo.hotel_manager.BuddyType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class BuddyRepoTest {

    @Autowired
    private BuddyRepo buddyRepo;

    private Buddy buddy;

    @BeforeEach
    void setUp() {
        buddy = Buddy.builder()
                .bt(BuddyType.ASSISTANT_DIRECTOR)
                .name("ads")
                .info("adss")
                .build();
    }

    @Test
    void esnureTest() {
        var saved = buddyRepo.save(buddy);

        assertThat(saved).isNotNull();
    }
}
