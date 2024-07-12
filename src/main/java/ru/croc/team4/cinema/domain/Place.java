package ru.croc.team4.cinema.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;
import ru.croc.team4.cinema.domain.converter.HashMapConverter;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "place")
public class Place {


    public Place(Row row, Status status, Category type, Integer placeNumber) {
        this.row = row;
        this.status = status;
        this.type = type;
        this.placeNumber = placeNumber;
    }

    public enum Status {
        FREE("свободно"), PAID("оплачено"), BOOKING("забронировано");
        public final String lable;

        Status(String lable) {
            this.lable = lable;
        }

        private static final Map<String, Status> LOOKUP_MAP = new HashMap<>();

        static {
            for (Status status : values()) {
                LOOKUP_MAP.put(status.lable, status);
            }
        }

        public static Status getStatusByString(String status) {
            return LOOKUP_MAP.get(status);
        }
    }


    @Id
    @UuidGenerator

    private UUID id;
    public void setId(UUID id) {this.id = id; }
    public UUID getId() {return this.id; }

    @Column(nullable = false)
    private Integer placeNumber;
    public void setPlaceNumber(Integer placeNumber) {this.placeNumber = placeNumber; }
    public Integer getPlaceNumber() {return this.placeNumber; }

    @Column
    @Enumerated(EnumType.ORDINAL)
    @Setter
    @Getter
    private Status status;

    @Column
    @Enumerated(EnumType.ORDINAL)
    @Setter
    @Getter
    private Category type;

    @ManyToOne
    @Setter
    @Getter
    @JoinColumn(name = "rowId", nullable = false)
    private Row row;

}
