package ru.croc.team4.cinema.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Getter
@Setter
public class Row {
    @Id
    @UuidGenerator
    private UUID id;
    @Column
    private int inSessionId;
    @ManyToOne
    @JoinColumn(name = "session_id", nullable = false)
    private Session session;
}
