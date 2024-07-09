package ru.croc.team4.cinema.domain;

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
    @JoinColumn(nullable = false, name = "movie_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Movie movie;
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

