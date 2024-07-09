package ru.croc.team4.administration.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.Duration;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Movie {

    @Id
    @UuidGenerator
    private UUID id;

    @Column(nullable = false, length = 32)
    private String title;

    @Column(nullable = false, columnDefinition = "INTERVAL")
    private Duration duration;

    @Column(length = 512)
    private String description;

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public Movie(String title, Duration duration, String description) {
        this.title = title;
        this.duration = duration;
        this.description = description;
    }

    public Movie() {
    }
}
