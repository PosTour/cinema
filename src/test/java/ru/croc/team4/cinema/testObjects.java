package ru.croc.team4.cinema;

import ru.croc.team4.cinema.domain.*;

import java.sql.Time;
import java.time.Duration;
import java.util.HashMap;

import java.util.Map;
import java.util.UUID;

public class testObjects {

    Map<String, String> map = new HashMap<>(){{put("1","1");}};
    {
        map.put("1", "3");
        map.put("2", "2");
    }

    public static Movie getMovie() {
        Movie movie = Movie.builder()
                .id(UUID.fromString("07c9903b-f2ba-42de-84ba-21896e514f83"))
                .duration(Duration.ofMinutes(122))
                .title("It")
                .description("great film")
                .build();
        return movie;
    }

    public static Movie getMovie2() {
        Movie movie = Movie.builder()
                .id(UUID.fromString("b6ed398b-c60f-49d9-a476-314cabe8bf5f"))
                .duration(Duration.ofMinutes(136))
                .title("Пираты карибского моря")
                .description("Тут описание фильм, которое нам нужно")
                .build();
        return movie;
    }


    public static Hall getHall() {

        Map<Integer, Integer> map = new HashMap<>();

        map.put(1, 3);
        map.put(2, 2);

        Hall hall = Hall.builder()
                .id(UUID.fromString("07c9903b-f2ba-42de-84ba-21896e514f83"))
                .name("Big hall")
                .seats(map)
                .build();

        return hall;
    }

    public static Session getSession() {
        Session session = Session.builder()
                .id(UUID.randomUUID())
                .movie(getMovie())
                .hall(getHall())
                .startTime(new Time(12,12,12))
                .endTime(new Time(12,12,12))
                .price(2000)
                .build();
        return session;
    }

    public static Place getPlace() {
        Place place = Place.builder()
                .id(UUID.randomUUID())
                .placeNumber(12)
                .status(Place.Status.PAID)
                .row(getRow())
                .build();
        return place;
    }

    public static Row getRow() {
        Row row = Row.builder()
                .id(UUID.randomUUID())
                .rowNumber(3)
                .session(getSession())
                .build();
        return row;
    }

    public static Ticket getTicket() {
        Ticket ticket = Ticket.builder()
                .id(UUID.randomUUID())
                .place(getPlace())
                .session(getSession())
                .user(getUser())
                .build();
        return ticket;
    }

    public static User getUser() {
        User user = User.builder()
                .id(UUID.randomUUID())
                .phone("789456123")
                .chatId(12345678)
                .build();
        return user;
    }
}
