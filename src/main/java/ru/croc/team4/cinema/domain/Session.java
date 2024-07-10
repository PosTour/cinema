package ru.croc.team4.cinema.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.sql.Time;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "session")
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

    public Session(Movie movie, Hall hall, Time startTime, Time endTime, Integer price) {
        this.movie = movie;
        this.hall = hall;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
    }
}

