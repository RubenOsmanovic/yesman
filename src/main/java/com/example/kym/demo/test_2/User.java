package com.example.kym.demo.test_2;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@DiscriminatorValue("USER")
public class User extends Person{

    @Column
    private String nickname;

    public User(String name, String nickname) {
        super(name);
        this.nickname = nickname;
    }
}
