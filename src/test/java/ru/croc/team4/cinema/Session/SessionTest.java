package ru.croc.team4.cinema.Session;

import com.google.gson.Gson;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.croc.team4.cinema.domain.*;
import ru.croc.team4.cinema.dto.SessionResponseDto;
import ru.croc.team4.cinema.repository.HallRepository;
import ru.croc.team4.cinema.repository.MovieRepository;
import ru.croc.team4.cinema.repository.PlaceRepository;
import ru.croc.team4.cinema.repository.SessionRepository;
import utils.ReadProperties;

import java.time.Duration;
import java.sql.Time;
import java.util.*;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@Epic("Интеграционный тест по SessionUtils, который отвечает за свободные места")
//@ActiveProfiles("test")
public class SessionTest {
//    @Autowired
//    private MovieRepository movieRepository;
//    @Autowired
//    private SessionRepository sessionRepository;
//    @Autowired
//    private HallRepository hallRepository;
//    @Autowired
//    private PlaceRepository placeRepository;
//
////    @LocalServerPort
//    private Integer port;
//
//    private final List<SessionResponseDto> resultSessions = new ArrayList<>();
//
////    @BeforeEach
//    public void setup() {
//        UUID uuid = UUID.randomUUID();
//        // количество мест в ряду
//        HashMap<Integer, Integer> seats = new HashMap<>();
//        seats.put(1, 3);
//        seats.put(2, 4);
//        seats.put(3, 4);
//
//        // создание зала
//        Hall hall = new Hall();
//        hall.setName("Зал 1");
//        hall.setSeats(seats);
//
//        // создание фильма
//        Movie movie = new Movie();
//        movie.setTitle("Пираты Карибского моря");
//        movie.setDescription("Тут какое-то длинное описание");
//        movie.setDuration(Duration.ofMinutes(123));
//
//        Movie movie1 = new Movie();
//        movie.setTitle("Интерстеллар");
//        movie.setDescription("Тут какое-то описание, оно недлинное");
//        movie.setDuration(Duration.ofMinutes(134));
//
//        // создание сеанса
//        Session session = new Session();
//        session.setId(uuid);
//        session.setMovie(movie);
//        session.setHall(hall);
//        session.setPrice(Integer.valueOf(100));
//        session.setStartTime(new Time(15, 20, 0));
//        session.setEndTime(new Time(17, 0, 0));
//
//        SessionResponseDto sessionResponseDto = new SessionResponseDto(session.getId(), session.getMovie(),
//                session.getHall(), session.getStartTime(), session.getPrice());
//
//        UUID uuid1 = UUID.randomUUID();
//        Session session1 = new Session();
//        session1.setId(uuid1);
//        session1.setMovie(movie1);
//        session1.setHall(hall);
//        session1.setPrice(Integer.valueOf(200));
//        session1.setStartTime(new Time(18, 40, 0));
//        session1.setEndTime(new Time(20, 54, 0));
//
//        SessionResponseDto sessionResponseDto2 = new SessionResponseDto(session1.getId(), session1.getMovie(),
//                session1.getHall(), session1.getStartTime(), session1.getPrice());
//
//        // создание мест
//        Place place = new Place();
//        for (int j = 1; j < session.getHall().getSeats().size(); j++) {
//            Row row = new Row();
//            row.setSession(session);
//            row.setRowNumber(String.valueOf(j));
//            /*for (int i = 0; i < session.getHall().getSeats().get(j); i++) {
//                place.setPlaceNumber(String.valueOf(i));
//                place.setOccupied(false);
//                place.setRow(row);
//            }*/
//        }
//
//        movieRepository.save(movie);
//        movieRepository.save(movie1);
//
//        hallRepository.save(hall);
//        placeRepository.save(place);
//
//        sessionRepository.save(session);
//        sessionRepository.save(session1);
//
//        resultSessions.add(sessionResponseDto);
//        resultSessions.add(sessionResponseDto2);
//    }
//
//    @Description("Проверка sessionRepostiroy, отвечающего за расчет свободных мест")
//    void CountFreePlacesTest() {
//
//    }
//
//
//    void findAllByMovieTest() {
//        RestAssured.baseURI = "https://localhost";
//        RestAssured.port = port;
//        RestAssured.useRelaxedHTTPSValidation();
//
//        if(Boolean.valueOf(String.valueOf(ReadProperties.propertiesRead().get("extended.log")))) RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
//
//        Response r = given()
//                .queryParam("movie", "Пираты")
//                .when()
//                .get("/api/session")
//                .then()
//                .extract().response();
//
//        List<SessionResponseDto> sessions = Collections.singletonList(new Gson().fromJson(r.getBody().asString(), SessionResponseDto.class));
//
//        assertAll(
//                () -> assertEquals(resultSessions, sessions)
//        );
//    }
}
