package ru.croc.team4.cinema.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.UuidGenerator;
import ru.croc.team4.cinema.domain.converter.HashMapConverter;
import ru.croc.team4.cinema.domain.converter.PricesConverter;

import java.sql.Date;
import java.sql.Time;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "session")
public class Session {
    @Id
    //@UuidGenerator
    private UUID id;
    @JoinColumn(nullable = false, name = "movie_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Movie movie;
    @JoinColumn(nullable = false)
    @OneToOne
    private Hall hall;
    @Column(nullable = false)
    private Time startTime;
    @Column(nullable = false)
    private Time endTime;
    @Column(nullable = false)
    private Date startDate;
    @Column(nullable = false)
    @Convert(converter = PricesConverter.class)
    private Map<Category, Integer> prices = new HashMap<>();
    @Column(nullable = false)
    @ColumnDefault("false")
    private Boolean isDeleted;

    public Session(Movie movie, Hall hall, Time startTime, Time endTime, Date startDate, Map<Category, Integer> prices, Boolean isDeleted) {
        this.movie = movie;
        this.hall = hall;
        this.startTime = startTime;
        this.endTime = endTime;
        this.startDate = startDate;
        this.prices = prices;
        this.isDeleted = isDeleted;
    }


    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }
}

