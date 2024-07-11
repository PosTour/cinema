//package ru.croc.team4.cinema.controller;
//
//import com.google.gson.Gson;
//import io.qameta.allure.Description;
//import io.restassured.RestAssured;
//import io.restassured.filter.log.RequestLoggingFilter;
//import io.restassured.filter.log.ResponseLoggingFilter;
//import io.restassured.response.Response;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.server.LocalServerPort;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.web.servlet.MockMvc;
//import org.testcontainers.shaded.com.google.common.reflect.TypeToken;
//import ru.croc.team4.cinema.domain.Hall;
//import ru.croc.team4.cinema.domain.Movie;
//import ru.croc.team4.cinema.domain.Row;
//import ru.croc.team4.cinema.domain.Session;
//import ru.croc.team4.cinema.dto.MovieDto;
//import ru.croc.team4.cinema.dto.MovieResponseDto;
//import ru.croc.team4.cinema.dto.SessionCreationDto;
//import ru.croc.team4.cinema.dto.SessionResponseDto;
//import ru.croc.team4.cinema.mapper.SessionMapper;
//import ru.croc.team4.cinema.mapper.SessionMapperImpl;
//import ru.croc.team4.cinema.repository.*;
//import ru.croc.team4.cinema.service.HallService;
//import ru.croc.team4.cinema.service.HallServiceImpl;
//import ru.croc.team4.cinema.service.SessionService;
//import ru.croc.team4.cinema.service.SessionServiceImpl;
//import ru.croc.team4.cinema.testObjects;
//import utils.ReadProperties;
//
//import java.lang.reflect.Type;
//import java.util.List;
//import java.util.UUID;
//
//import static io.restassured.RestAssured.given;
//import static org.junit.jupiter.api.Assertions.assertAll;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureMockMvc
//@ActiveProfiles("test")
//public class SessionControllerTest {
//    @LocalServerPort
//    private Integer port;
//
//    @Autowired
//    private SessionRepository sessionRepository;
//
//    private final Gson gson = new Gson();
//
//    private final SessionMapper sessionMapper;
//
//    @Autowired
//    private HallServiceImpl hallServiceImpl;
//    @Autowired
//    private HallRepository hallRepository;
//    @Autowired
//    private MovieRepository movieRepository;
//    @Autowired
//    private RowRepository rowRepository;
//    @Autowired
//    private PlaceRepository placeRepository;
//
//    public SessionControllerTest() {
//        this.sessionMapper = new SessionMapperImpl();
//    }
//
//    @BeforeEach
//    public void setup() {
//        // создаем настройки
//        RestAssured.baseURI = "http://localhost";
//        RestAssured.port = port;
//        RestAssured.useRelaxedHTTPSValidation();
//        Hall hall = testObjects.getHall();
//        hallRepository.save(hall);
//
//        Movie movie = testObjects.getMovie();
//        Row row = testObjects.getRow(hall, movie);
//        rowRepository.save(row);
//
//        // для того чтобы не засорять консоль
//        if(Boolean.valueOf(String.valueOf(ReadProperties.propertiesRead().get("extended.log")))) RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
//
//        Session session = testObjects.getSession(hall, movie);
//        sessionRepository.save(session);
//    }
//
//    @AfterEach
//    public void clean() {
//        sessionRepository.deleteAllInBatch();
//    }
//
//    @Test
//    @Description("Тест на создание сеанса")
//    public void createSessionTest() {
//        SessionCreationDto sessionCreationDto = sessionMapper
//                .sessionToSessionCreationDto(testObjects.getSession2(), hallServiceImpl);
//
//        String sessionJson = gson.toJson(sessionCreationDto);
//
//        Response r = given()
//                .header("Content-Type", "application/json")
//                .body(sessionJson)
//                .post("/api/session")
//                .then()
//                .extract().response();
//
//        SessionResponseDto sessionResponseDto = gson.fromJson(r.asString(), SessionResponseDto.class);
//
//        assertAll(
//                () -> assertEquals(sessionCreationDto.movieId(), sessionResponseDto.movieId(), "Не совпадают фильмы"),
//                () -> assertEquals(sessionCreationDto.startTime(), sessionResponseDto.startTime(), "Не совпадает время начало фильма"),
//                () -> assertEquals(sessionCreationDto.prices(), sessionResponseDto.prices(), "Не совпадает цена за билет")
//        );
//    }
//
//    @Test
//    @Description("Тест на удаление сеанса")
//    public void deleteSessionTest() {
//        List<SessionResponseDto> sessionResponseDtos = getAllSessions();
//
//        UUID id = sessionResponseDtos.get(0).id();
//        // необходимое кол-во фильмов после удаления одного фильма
//        int checkSessionCount = sessionResponseDtos.size() - 1;
//
//        Response r = given()
//                .delete("/api/hall/" + id)
//                .then()
//                .extract().response();
//
//        int resultSessionCount = getAllSessions().size();
//
//        assertEquals(checkSessionCount, resultSessionCount, "Не совпадает количество сеансов в бд");
//    }
//
//    @Test
//    @Description("Тест получения всех сеансов")
//    public void getAllSessionTest() {
//        List<SessionResponseDto> sessionResponseDtos = getAllSessions();
//
//        assertEquals(1, sessionResponseDtos.size(), "Неверное кол-во сеансов");
//    }
//
//    @Test
//    @Description("Тест получения сеанса по Id")
//    public void getSessionByIdTest() {
//        List<SessionResponseDto> sessionResponseDtos = getAllSessions();
//
//        UUID id = sessionResponseDtos.get(0).id();
//
//        Response r = given()
//                .get("/api/session/findById/" + id)
//                .then()
//                .extract().response();
//
//        SessionResponseDto sessionResponseDto = gson.fromJson(r.asString(), SessionResponseDto.class);
//
//        assertAll(
//                () -> assertEquals("Big hall", sessionResponseDto.hallName(), "Неверное название зала")
//        );
//    }
//
//    @Test
//    @Description("Тест получения сеанса по фильму")
//    public void getSessionsByMovieTest() {
//        List<SessionResponseDto> sessionResponseDtos = getAllSessions();
//        SessionResponseDto sessionResponseDto = sessionResponseDtos.get(0);
//
//        UUID idMovie = sessionResponseDto.movieId();
//
//        Response r = given()
//                .get("/api/session/findByMovieId/" + idMovie)
//                .then()
//                .extract().response();
//
//        SessionResponseDto sessionResponse = gson.fromJson(r.asString(), SessionResponseDto.class);
//
//        assertAll(
//                () -> assertEquals("Big hall", sessionResponseDto.hallName(), "Неверное название зала")
//        );
//    }
//
//    @Test
//    @Description("Тест на обновления сеанса")
//    public void updateSessionTest() {
//        SessionCreationDto sessionCreationDto = sessionMapper
//                .sessionToSessionCreationDto(testObjects.getSessionUpdate(), hallServiceImpl);
//
//        List<SessionResponseDto> sessionResponseDtos = getAllSessions();
//        UUID id = sessionResponseDtos.get(0).id();
//
//        String sessionJson = gson.toJson(sessionCreationDto);
//
//        Response r = given()
//                .header("Content-Type", "application/json")
//                .body(sessionJson)
//                .put("/api/movie/" + id)
//                .then()
//                .extract().response();
//
//        SessionResponseDto sessionResponseDto = gson.fromJson(r.asString(), SessionResponseDto.class);
//
//        assertAll(
//                () -> assertEquals(sessionCreationDto.prices(), sessionResponseDto.prices(), "Не совпадает цена фильмов"),
//                () -> assertEquals(sessionCreationDto.startTime(), sessionResponseDto.startTime(), "Не совпадает время начало фильма")
//        );
//    }
//
//    private List<SessionResponseDto> getAllSessions() {
//        // запрос по пути: localhost:8080/api/movie/all
//        Response r = given()
//                .when()
//                .get("/api/sessions/all")
//                .then()
//                .extract().response();
//
//        // для того чтобы определить тип объекта json
//        Type sessionResponseDtoListType = new TypeToken<List<SessionResponseDto>>() {}.getType();
//
//        return new Gson().fromJson(r.getBody().asString(), sessionResponseDtoListType);
//    }
//}
