package com.example.kym.demo.registration;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetingRep extends JpaRepository<Meeting, Long> {
}
