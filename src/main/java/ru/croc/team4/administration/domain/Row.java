package ru.croc.team4.administration.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Row {
    @Id
    private int id;

    @Column(nullable = false)
    private int freePlaces;

    @ManyToOne
    @JoinColumn(name = "session_id", nullable = false)
    private Session session;
}
