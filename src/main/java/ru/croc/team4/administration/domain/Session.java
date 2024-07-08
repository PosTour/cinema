package ru.croc.team4.administration.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
    Movie movie;
    @Column(nullable = false)
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
