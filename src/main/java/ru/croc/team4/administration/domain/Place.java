package ru.croc.team4.administration.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(nullable = false)
    private int inRowId;

    @Column()
    private boolean isOccupied;

    @ManyToOne
    @JoinColumn(name = "rowId", nullable = false)
    private Row row;
}
