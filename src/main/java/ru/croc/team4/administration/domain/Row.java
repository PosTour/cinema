package ru.croc.team4.administration.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
public class Row {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    @Column
    private int inSessionId;
    @ManyToOne
    @JoinColumn(name = "session_id", nullable = false)
    private Session session;
}
