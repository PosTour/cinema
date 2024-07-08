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
    UUID id;
    @JoinColumn(nullable = false)
    @OneToOne
    Movie movie;
    @JoinColumn(nullable = false)
    @OneToOne
    Hall hall;
    @Column(nullable = false)
    Time startTime; //либо используем другой формат даты
    @Column(nullable = false)
    Time endTime;
    @Column(nullable = false)
    Integer price;
    @Column(nullable = false)
    Integer freePlaces;
}

