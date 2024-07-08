package ru.croc.team4.administration.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private int inRowId;

    @Column()
    private boolean occupied;

    @ManyToOne
    @JoinColumn(name = "rowId", nullable = false)
    private Row row;
}
