package ru.croc.team4.cinema.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.Duration;
import java.util.UUID;

@Entity
@Builder
@AllArgsConstructor
@Table(name = "movie")
public class Movie {

    @Id
    //@UuidGenerator
    private UUID id;

    @Column(nullable = false, length = 32)
    private String title;

    @Column(nullable = false, columnDefinition = "NUMERIC")
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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
