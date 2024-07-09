package ru.croc.team4.administration.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.sql.Time;
import java.util.UUID;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Session {
    @Id
    @UuidGenerator
    private UUID id;
    @JoinColumn(nullable = false)
    private String movie;
    @JoinColumn(nullable = false)
    @OneToOne
    private Hall hall;
    @Column(nullable = false)
    private Time startTime; //либо используем другой формат даты
    @Column(nullable = false)
    private Time endTime;
    @Column(nullable = false)
    private Integer price;
}

