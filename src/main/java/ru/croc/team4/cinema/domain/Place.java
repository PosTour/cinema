package ru.croc.team4.cinema.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Getter
@Setter
public class Place {
    @Id
    @UuidGenerator

    private UUID id;

    @Column(nullable = false, length = 32)
    private String placeNumber;

    @Column()
    private boolean isOccupied;

    @ManyToOne
    @JoinColumn(name = "rowId", nullable = false)
    private Row row;
}
