package ru.croc.team4.cinema.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@Table(name = "row")
public class Row {
    @Id
    @UuidGenerator
    private UUID id;
    @Column(nullable = false, length = 32)
    private Integer rowNumber;
    @ManyToOne
    @JoinColumn(name = "session_id", nullable = false)
    private Session session;

    public Row() {

    }

    public Row(Session session, Integer rowNumber) {
        this.session = session;
        this.rowNumber = rowNumber;
    }
}
