package com.example.kym.demo.exam;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "student")
public class Student {

    @Id @Column(nullable = false, unique = true)
    private String studentId;

    private String firstName;

    @Column(nullable = false)
    private String lastName;

    private String birthDate;

}
