package ru.croc.team4.cinema.controller;

import com.google.gson.Gson;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;
import org.testcontainers.shaded.com.google.common.reflect.TypeToken;
import ru.croc.team4.cinema.domain.Hall;
import ru.croc.team4.cinema.dto.HallDto;
import ru.croc.team4.cinema.dto.HallResponseDto;
import ru.croc.team4.cinema.dto.MovieResponseDto;
import ru.croc.team4.cinema.mapper.HallMapper;
import ru.croc.team4.cinema.mapper.HallMapperImpl;
import ru.croc.team4.cinema.repository.HallRepository;
import ru.croc.team4.cinema.testObjects;
import utils.ReadProperties;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Feature("Тесты для HallController (создание, получение одного, получение всех, обновление)")
public class HallControllerTest {
    @LocalServerPort
    private Integer port;

    private final Gson gson = new Gson();

    private HallMapper hallMapper;

    public HallControllerTest() {
        this.hallMapper = new HallMapperImpl();
    }

    @Autowired
    private HallRepository hallRepository;

    @BeforeEach
    public void setup() {
        // создаем настройки
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
        RestAssured.useRelaxedHTTPSValidation();

        // для того чтобы не засорять консоль
        if(Boolean.valueOf(String.valueOf(ReadProperties.propertiesRead().get("extended.log")))) RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

        Hall hall = testObjects.getHall();
        Hall hall2 = testObjects.getHall2();
        hallRepository.save(hall);
        hallRepository.save(hall2);
    }

    @AfterEach
    public void cleanup() {
        hallRepository.deleteAllInBatch();
    }

    @Test
    @Description("Тест на получение конкретного зала")
    public void getHallTest() {
        List<HallResponseDto> hallResponseDtos = getAllHalls();

        UUID id = hallResponseDtos.get(1).id();

        Response r = given()
                .get("/api/halls/" + id)
                .then()
                .extract().response();

        HallResponseDto hallResponseDto = gson.fromJson(r.getBody().asString(), HallResponseDto.class);

        Map<Integer, Integer> map = new HashMap<>();

        map.put(1, 3);
        map.put(2, 2);

        assertAll(
                () -> assertEquals("Еще больше зал", hallResponseDto.name(), "Неверное название зала"),
                () -> assertEquals(map, hallResponseDto.seats(), "Неверное количество и расположение мест")
        );
    }

    @Test
    @Description("Тест на обновление зала")
    public void UpdateHallTest() {
        HallDto hallDto = hallMapper.hallToHallDto(testObjects.getHallUpdate());

        List<HallResponseDto> hallResponseDtos = getAllHalls();

        UUID id = hallResponseDtos.get(1).id();

        String hallJson = gson.toJson(hallDto);

        Response r = given()
                .header("Content-Type", "application/json")
                .body(hallJson)
                .put("/api/hall/" + id)
                .then()
                .extract().response();

        hallResponseDtos = getAllHalls();
        HallResponseDto hallResponseDto = hallResponseDtos.get(1);

        Map<Integer, Integer> map = new HashMap<>();

        map.put(1, 5);
        map.put(2, 5);

        assertAll(
                () -> assertEquals("Еще больше зал 2", hallResponseDto.name(), "Неверно указано название зала"),
                () -> assertEquals(map, hallResponseDto.seats(), "Неверное количество и расположение мест ")
        );
    }

    @Test
    @Description("Тест на создание зала")
    public void createHallTest() {
        HallDto hallDto = hallMapper.hallToHallDto(testObjects.getHallUpdate());
        String hallJson = gson.toJson(hallDto);

        Response r = given()
                .header("Content-Type", "application/json")
                .body(hallJson)
                .post("/api/hall")
                .then()
                .extract().response();

        HallResponseDto hallResponseDto = gson.fromJson(r.getBody().asString(), HallResponseDto.class);

        assertAll(
                () -> assertEquals("Еще больше зал 2", hallResponseDto.name(), "Неверное название фильма"),
                () -> assertEquals(hallDto.seats(), hallResponseDto.seats(), "Неверное количество и расположение мест")
        );
    }

    @Test
    @Description("Тест на получение всех залов")
    public void getAllHallsTest() {
        List<HallResponseDto> hallResponseDtos = getAllHalls();

        // проверяем на количество залов
        assertEquals(1, hallResponseDtos.size(), "Неверное количество залов");
    }

    private List<HallResponseDto> getAllHalls() {
        // запрос по пути: localhost:8080/api/movie/all
        Response r = given()
                .when()
                .get("/api/hall/all")
                .then()
                .extract().response();

        // для того чтобы определить тип объекта json
        Type hallResponseDtoListType = new TypeToken<List<HallResponseDto>>() {}.getType();

        return new Gson().fromJson(r.getBody().asString(), hallResponseDtoListType);
    }
}
