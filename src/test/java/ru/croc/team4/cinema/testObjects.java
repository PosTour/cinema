package ru.croc.team4.cinema;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.croc.team4.cinema.domain.*;
import ru.croc.team4.cinema.repository.*;

import java.sql.Date;
import java.sql.Time;
import java.time.Duration;
import java.util.HashMap;

import java.util.Map;
import java.util.UUID;

@Service
public class testObjects {

    @Autowired
    private HallRepository hallRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private RowRepository rowRepository;
    @Autowired
    private PlaceRepository placeRepository;

    @BeforeEach
    public void setup() {
        Hall hall = testObjects.getHall();
        hallRepository.save(hall);


        Movie movie = testObjects.getMovie();
        movieRepository.save(movie);

        Session session = testObjects.getSession();
        sessionRepository.save(session);

        Row row = testObjects.getRow();
        rowRepository.save(row);
    }

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
        Map<Integer, Map<Integer, Category>> map = new HashMap<>();

        Map<Integer, Category> map2 = new HashMap<>();
        Map<Integer, Category> map3 = new HashMap<>();

        map2.put(1, Category.BAD);
        map2.put(2, Category.GOOD);

        map3.put(1, Category.BAD);
        map3.put(2, Category.BAD);
        map3.put(3, Category.EXCELLENT);
        map.put(1, map2);
        map.put(2, map3);

        Hall hall = Hall.builder()
                .id(UUID.fromString("07c9903b-f2ba-42de-84ba-21896e514f83"))
                .name("Big hall")
                .seats(map)
                .build();

        System.out.println(hall.getId() + " " + hall.getName() + " " + hall.getSeats());
        return hall;
    }

    public static Hall getHall2() {

        Map<Integer, Map<Integer, Category>> map = new HashMap<>();

        Map<Integer, Category> map2 = new HashMap<>();
        Map<Integer, Category> map3 = new HashMap<>();

        map2.put(1, Category.BAD);
        map2.put(2, Category.GOOD);

        map3.put(1, Category.BAD);
        map3.put(2, Category.BAD);
        map3.put(3, Category.EXCELLENT);
        map.put(1, map2);
        map.put(2, map3);

        Hall hall = Hall.builder()
                .id(UUID.fromString("d38ce817-3964-4c06-983c-8a92df5b1a3f"))
                .name("Еще больше зал")
                .seats(map)
                .build();

        System.out.println(hall.getId() + " " + hall.getName() + " " + hall.getSeats());
        return hall;
    }

    public static Hall getHallUpdate() {

        Map<Integer, Map<Integer, Category>> map = new HashMap<>();

        Map<Integer, Category> map2 = new HashMap<>();
        Map<Integer, Category> map3 = new HashMap<>();

        map2.put(1, Category.BAD);
        map2.put(2, Category.GOOD);

        map3.put(1, Category.BAD);
        map3.put(2, Category.BAD);
        map3.put(3, Category.EXCELLENT);
        map.put(1, map2);
        map.put(2, map3);

        Hall hall = Hall.builder()
                .id(UUID.fromString("d38ce817-3964-4c06-983c-8a92df5b1a3f"))
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
                .startDate(new Date(1222))
                .isDeleted(false)
                .prices(map)
                .build();
        System.out.println(session.getHall().getId());
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
                .phone("79647620719")
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
