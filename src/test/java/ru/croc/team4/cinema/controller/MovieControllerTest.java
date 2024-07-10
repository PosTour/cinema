package ru.croc.team4.cinema.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.google.common.reflect.TypeToken;
import ru.croc.team4.cinema.domain.Movie;
import ru.croc.team4.cinema.dto.MovieDto;
import ru.croc.team4.cinema.dto.MovieResponseDto;
import ru.croc.team4.cinema.mapper.MovieMapper;
import ru.croc.team4.cinema.mapper.MovieMapperImpl;
import ru.croc.team4.cinema.repository.MovieRepository;
import ru.croc.team4.cinema.testObjects;
import utils.ReadProperties;

import java.lang.reflect.Type;
import java.util.List;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Feature("Тест для MovieController")
public class MovieControllerTest {
    @LocalServerPort
    private Integer port;

    @Autowired
    private ObjectMapper oMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MovieRepository movieRepository;

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
        RestAssured.useRelaxedHTTPSValidation();

        if(Boolean.valueOf(String.valueOf(ReadProperties.propertiesRead().get("extended.log")))) RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

        Movie movie = testObjects.getMovie();
        Movie movie2 = testObjects.getMovie2();
        movieRepository.save(movie);
        movieRepository.save(movie2);
    }

    @AfterEach
    public void cleanup() {
        movieRepository.deleteAllInBatch();
    }

    @Test
    @Description("Тест на создание фильма в бд")
    public void createMovieTest() throws Exception {
        MovieMapper movieMapper = new MovieMapperImpl();
        Gson gson = new Gson();

        MovieDto movieDto = movieMapper.movieToMovieDto(testObjects.getMovie2());
        String movieJson = gson.toJson(movieDto);

        Response r = given()
                .header("Content-Type", "application/json")
                .body(movieJson)
                .post("/api/movie")
                .then()
                .extract().response();

        MovieResponseDto movieResponseDto = gson.fromJson(r.asString(), MovieResponseDto.class);

        assertAll(
                () -> assertEquals("Пираты карибского моря", movieResponseDto.title(),
                        "Неверное название фильма")
        );
    }

    @Test
    @Description("Тест на получение корректного фильма из бд")
    public void getMovieTest() {

        Response r = given()
                .when()
                .get("/api/movie/all")
                .then()
                .extract().response();

        // для того чтобы определить тип json
        Type MovieResponseDtoListType = new TypeToken<List<MovieResponseDto>>() {}.getType();

        List<MovieResponseDto> movies = new Gson().fromJson(r.getBody().asString(), MovieResponseDtoListType);

        // ищем необходимый фильм из всех
        MovieResponseDto movie = movies.stream().filter(value -> value.title().equals("It")).findFirst().get();

        assertAll(
                () -> assertEquals("It", movie.title(), "Неверное название фильма"),
                () -> assertEquals(122, movie.durationInMinutes(), "Неверная длительность фильма"),
                () -> assertEquals("great film", movie.description(), "Неверное описание фильма")
        );
    }
}
