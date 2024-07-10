package ru.croc.team4.cinema.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;
import ru.croc.team4.cinema.domain.converter.HashMapConverter;

import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "place")
public class Place {

    public enum Status {
        FREE, PAID, BOOKING;
    }

    @Id
    @UuidGenerator

    private UUID id;

    @Column(nullable = false)
    private Integer placeNumber;

    @Enumerated(EnumType.ORDINAL)
    private Status status;


    @ManyToOne
    @JoinColumn(name = "rowId", nullable = false)
    private Row row;
}
