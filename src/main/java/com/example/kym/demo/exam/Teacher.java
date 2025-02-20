package com.example.kym.demo.exam;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Table(name = "teacher")
@Entity
public class Teacher {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(nullable = false, unique = true)
    private String teacherId;

    private String firstName;

    @Column(nullable = false)
    private String lastName;
}
