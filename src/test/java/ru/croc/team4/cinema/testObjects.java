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
                .id(UUID.fromString("07f251f4-23da-47ce-a4a0-683613601029"))
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
                .description("Тут описание фильма, которое нам нужно")
                .build();
        return movie;
    }

    public static Movie getMovieUpdate() {
        Movie movie = Movie.builder()
                .id(UUID.fromString("b6ed398b-c60f-49d9-a476-314cabe8bf5f"))
                .duration(Duration.ofMinutes(130))
                .title("Пираты карибского моря 2")
                .description("Тут описание фильма, которое нам нужно")
                .build();
        return movie;
    }

    public static Movie getMovieUpdateNegative() {
        Movie movie = Movie.builder()
                .id(UUID.fromString("b6ed398b-c60f-49d9-a476-314cabe8bf5f"))
                .duration(Duration.ofMinutes(-10))
                .title("")
                .description("Тут описание фильма, которое нам нужно")
                .build();
        return movie;
    }


    public static Hall getHall() {

        Map<Map<Integer, Integer>, String> map = new HashMap<>();
        Map<Integer, Integer> map1 = new HashMap<>(1,1);
        Map<Integer, Integer> map2 = new HashMap<>(1,2);

        map.put(map1, "g");
        map.put(map2, "b");

        Hall hall = Hall.builder()
                .id(UUID.fromString("07f251f4-23da-47ce-a4a0-683613601029"))
                .name("Big hall")
                .seats(map)
                .build();

        System.out.println(hall.getId() + " " + hall.getName() + " " + hall.getSeats());
        return hall;
    }

    public static Hall getHall2() {

        Map<Map<Integer, Integer>, String> map = new HashMap<>();
        Map<Integer, Integer> map1 = new HashMap<>(1,1);
        Map<Integer, Integer> map2 = new HashMap<>(1,2);

        map.put(map1, "g");
        map.put(map2, "b");

        Hall hall = Hall.builder()
                .id(UUID.fromString("07c9903b-f2ba-42de-84ba-21896e514f83"))
                .name("Еще больше зал")
                .seats(map)
                .build();

        System.out.println(hall.getId() + " " + hall.getName() + " " + hall.getSeats());
        return hall;
    }

    public static Hall getHallUpdate() {

        Map<Map<Integer, Integer>, String> map = new HashMap<>();
        Map<Integer, Integer> map1 = new HashMap<>(1,1);
        Map<Integer, Integer> map2 = new HashMap<>(1,2);

        map.put(map1, "g");
        map.put(map2, "b");

        Hall hall = Hall.builder()
                .id(UUID.fromString("07c9903b-f2ba-42de-84ba-21896e514f83"))
                .name("Еще больше зал 2")
                .seats(map)
                .build();

        System.out.println(hall.getId() + " " + hall.getName() + " " + hall.getSeats());
        return hall;
    }

    public static Session getSession() {
        Map<Category, Integer> map = new HashMap<>();
        map.put(Category.BAD, 87);
        map.put(Category.EXCELLENT, 87);
        map.put(Category.GOOD, 87);
        Session session = Session.builder()
                .id(UUID.fromString("07f251f4-23da-47ce-a4a0-683613601029"))
                .movie(getMovie())
                .hall(getHall())
                .startTime(new Time(12,12,12))
                .endTime(new Time(14,35,3))
                .prices(map)
                .build();
        return session;
    }

    public static Session getSession2() {
        Map<Category, Integer> map = new HashMap<>();
        map.put(Category.BAD, 87);
        map.put(Category.EXCELLENT, 87);
        map.put(Category.GOOD, 87);
        Session session = Session.builder()
                .id(UUID.fromString("07f251f4-23da-47ce-a4a0-683613601029"))
                .movie(getMovie2())
                .hall(getHallUpdate())
                .startTime(new Time(12,12,12))
                .endTime(new Time(14,35,3))
                .prices(map)
                .build();
        return session;
    }

    public static Session getSessionUpdate() {
            Map<Category, Integer> map = new HashMap<>();
            map.put(Category.BAD, 87);
            map.put(Category.EXCELLENT, 87);
            map.put(Category.GOOD, 87);
        Session session = Session.builder()
                .id(UUID.fromString("07f251f4-23da-47ce-a4a0-683613601029"))
                .movie(getMovie2())
                .hall(getHallUpdate())
                .startTime(new Time(13,0,12))
                .endTime(new Time(14,35,3))
                .prices(map)
                .build();
        return session;
    }

    public static Place getPlace() {
        Place place = Place.builder()
                .id(UUID.fromString("07f251f4-23da-47ce-a4a0-683613601029"))
                .placeNumber(12)
                .status(Place.Status.PAID)
                .row(getRow())
                .build();
        return place;
    }

    public static Row getRow() {
        Row row = Row.builder()
                .id(UUID.fromString("07f251f4-23da-47ce-a4a0-683613601029"))
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
                .phone("789456123")
                .chatId(12345678)
                .build();
        return user;
    }

    public static User getUser2() {
        User user = User.builder()
                .phone("79947653248")
                .chatId(7265432)
                .build();
        return user;
    }
}
