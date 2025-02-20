package com.example.kym.demo.hotel_manager;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder

@Entity
@Table(name = "dive")
public class Dive extends AbstractPersistable<Long> {

    @Column(nullable = false)
    private LocalDateTime date;

    @Column
    private DiverProfile diverProfile;

    @Builder.Default
    @OneToMany(mappedBy = "dive", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Equipment> equipmentList = new ArrayList<>();

    public Dive addEq(Equipment... eq) {
        equipmentList.addAll(Arrays.asList(eq));
        return this;
    }

    public Dive addListEq(List<Equipment> collEq) {
        equipmentList.addAll(collEq);
        return this;
    }

    public Dive removeEq(List<Equipment> collEq) {
        equipmentList.removeAll(collEq);
        return this;
    }
}
