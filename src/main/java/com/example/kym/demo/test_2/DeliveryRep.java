package com.example.kym.demo.test_2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRep extends JpaRepository<Delivery, Long> {
}
