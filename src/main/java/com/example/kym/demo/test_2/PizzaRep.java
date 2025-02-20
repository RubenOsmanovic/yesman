package com.example.kym.demo.test_2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PizzaRep extends JpaRepository<Pizza, Long> {

    Optional<Pizza> findByName(String name);

}
