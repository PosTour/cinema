package ru.croc.team4.cinema.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "place")
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
