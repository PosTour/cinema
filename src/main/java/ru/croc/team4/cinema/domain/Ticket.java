package ru.croc.team4.cinema.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Builder
@AllArgsConstructor
@Table(name = "ticket")
public class Ticket {

    @Id
    @UuidGenerator
    private UUID id;
    public UUID getId() {return this.id; }

    @ManyToOne
    @JoinColumn(name = "user_chatId")
    private User user;
    public void setUser(User bookingCode) {this.user = user;}
    public User getUser() {return this.user; }

    @Column(nullable = false)
    private String bookingCode;
    public void setBookingCode(String bookingCode) {this.bookingCode = bookingCode;}
    public String getBookingCode() {return this.bookingCode; }

    @ManyToOne
    @JoinColumn(name = "session_id")
    private Session session;

    public void setSession(Session session) {this.session = session;}
    public Session getSession() {return this.session;}
    @OneToOne
    @JoinColumn(name = "place_id")
    private Place place;

    public void setPlace(Place place) {this.place = place;}
    public Place getPlace() {return this.place;}


    public Ticket() {}

    public Ticket(User user, Session session, Place place) {
        this.user = user;
        this.session = session;
        this.place = place;
    }


}
