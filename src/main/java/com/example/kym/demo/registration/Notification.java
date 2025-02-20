package com.example.kym.demo.registration;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Table(name = "notification")
@Entity
public class Notification extends AbstractPersistable<Long> {

    @ManyToOne
    @JoinColumn(name = "registration_id")
    private Registration registration;

    private NotificationType type;

}
