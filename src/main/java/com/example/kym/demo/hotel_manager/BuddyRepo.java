package com.example.kym.demo.hotel_manager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuddyRepo extends JpaRepository<Buddy, Integer> {
}
