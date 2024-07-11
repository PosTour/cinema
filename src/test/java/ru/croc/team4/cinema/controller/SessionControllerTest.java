package ru.croc.team4.cinema.controller;

import com.google.gson.Gson;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.google.common.reflect.TypeToken;
import ru.croc.team4.cinema.domain.Session;
import ru.croc.team4.cinema.dto.MovieResponseDto;
import ru.croc.team4.cinema.dto.SessionCreationDto;
import ru.croc.team4.cinema.dto.SessionResponseDto;
import ru.croc.team4.cinema.mapper.SessionMapper;
import ru.croc.team4.cinema.mapper.SessionMapperImpl;
import ru.croc.team4.cinema.repository.SessionRepository;
import ru.croc.team4.cinema.testObjects;

import java.lang.reflect.Type;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
public class SessionControllerTest {
    @LocalServerPort
    private Integer port;

    @Autowired
    private SessionRepository sessionRepository;

    private final Gson gson = new Gson();

    private final SessionMapper sessionMapper;

    public SessionControllerTest() {
        this.sessionMapper = new SessionMapperImpl();
    }

    @BeforeEach
    public void setup() {
        Session session = new Session();
        sessionRepository.save(session);
    }

    @AfterEach
    public void clean() {
        sessionRepository.deleteAllInBatch();
    }

    @Test
    @Description("Тест на создание сеанса")
    public void createSessionTest() {
        SessionCreationDto sessionCreationDto = sessionMapper.sessionToSessionCreationDto(testObjects.getSession2())
    }

    @Test
    @Description("Тест на удаление сеанса")
    public void deleteSessionTest() {

    }

    @Test
    @Description("Тест получения всех сеансов")
    public void getAllSessionTest() {
        List<SessionResponseDto> sessionResponseDtos = getAllSessions();

        assertEquals(1, sessionResponseDtos.size(), "Неверное кол-во сеансов");
    }

    @Test
    @Description("Тест получения сеанса по Id")
    public void getSessionByIdTest() {

    }

    @Test
    @Description("Тест получения сеанса по фильму")
    public void getSessionsByMovieTest() {

    }

    @Test
    @Description("Тест на обновления сеанса")
    public void updateSessionTest() {

    }

    private List<SessionResponseDto> getAllSessions() {
        // запрос по пути: localhost:8080/api/movie/all
        Response r = given()
                .when()
                .get("/api/sessions/all")
                .then()
                .extract().response();

        // для того чтобы определить тип объекта json
        Type sessionResponseDtoListType = new TypeToken<List<SessionResponseDto>>() {}.getType();

        return new Gson().fromJson(r.getBody().asString(), sessionResponseDtoListType);
    }
}
