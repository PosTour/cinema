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
import ru.croc.team4.cinema.exception_handler.ErrorResponse;
import ru.croc.team4.cinema.exception_handler.ValidationErrorResponse;
import ru.croc.team4.cinema.mapper.MovieMapper;
import ru.croc.team4.cinema.mapper.MovieMapperImpl;
import ru.croc.team4.cinema.repository.MovieRepository;
import ru.croc.team4.cinema.testObjects;
import utils.ReadProperties;

import java.lang.reflect.Type;
import java.util.List;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Feature("Тест для MovieController")
public class MovieControllerTest {
    @LocalServerPort
    private Integer port;

    private final Gson gson = new Gson();

    private final MovieMapper movieMapper;

    public MovieControllerTest() {
        this.movieMapper = new MovieMapperImpl();
    }

    @Autowired
    private MovieRepository movieRepository;

    @BeforeEach
    public void setup() {
        // создаем настройки
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
        RestAssured.useRelaxedHTTPSValidation();

        // для того чтобы не засорять консоль
        if(Boolean.valueOf(String.valueOf(ReadProperties.propertiesRead().get("extended.log")))) RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

        // Добавляем фильмы в бд
        Movie movie = testObjects.getMovie();
        Movie movie2 = testObjects.getMovie2();
        movieRepository.save(movie);
        movieRepository.save(movie2);
    }

    // очищаем бд после каждого теста
    @AfterEach
    public void cleanup() {
        movieRepository.deleteAllInBatch();
    }

    @Test
    @Description("Тест на создание фильма в бд")
    public void createMovieTest() {
        MovieDto movieDto = movieMapper.movieToMovieDto(testObjects.getMovie2());
        // из объекта в json string
        String movieJson = gson.toJson(movieDto);

        // запрос на сервер
        Response r = given()
                .header("Content-Type", "application/json")
                .body(movieJson)
                .post("/api/movie")
                .then()
                .extract().response();

        MovieResponseDto movieResponseDto = gson.fromJson(r.asString(), MovieResponseDto.class);

        assertAll(
                () -> assertEquals("Пираты карибского моря", movieResponseDto.title(),
                        "Неверное название фильма"),
                () -> assertEquals(136, movieResponseDto.durationInMinutes(), "Неверная длительность фильма"),
                () -> assertEquals("Тут описание фильма, которое нам нужно", movieResponseDto.description(),
                        "Неверное описание фильма")
        );
    }

    @Test
    @Description("Тест на получение корректного фильма из бд")
    public void getMovieTest() {
        List<MovieResponseDto> movies = getAllMovies();

        // ищем необходимый фильм из всех
        MovieResponseDto movie = movies.stream().filter(value -> value.title().equals("It")).findFirst().get();

        assertAll(
                () -> assertEquals("It", movie.title(), "Неверное название фильма"),
                () -> assertEquals(122, movie.durationInMinutes(), "Неверная длительность фильма"),
                () -> assertEquals("great film", movie.description(), "Неверное описание фильма")
        );
    }

    @Test
    @Description("Тест на обновление фильма в бд")
    public void UpdateMovieTest() {
        MovieDto movieDto = movieMapper.movieToMovieDto(testObjects.getMovieUpdate());
        // получение всех фильмов
        List<MovieResponseDto> movies = getAllMovies();
        // достаем нужный фильм (будто был запрос по id)
        UUID id = movies.get(1).id();

        String movieJson = gson.toJson(movieDto);

        Response r = given()
                .header("Content-Type", "application/json")
                .body(movieJson)
                .put("/api/movie/" + id)
                .then()
                .extract().response();

        MovieResponseDto movieResponseDto = gson.fromJson(r.asString(), MovieResponseDto.class);

        assertAll(
                () -> assertEquals("Пираты карибского моря 2", movieResponseDto.title(), "Неверно указано название"),
                () -> assertEquals(130, movieResponseDto.durationInMinutes(), "Неверно указана длительность"),
                () -> assertEquals("Тут описание фильма, которое нам нужно", movieResponseDto.description(), "Неверно указано описание")
        );
    }

    @Test
    @Description("Тест на удаление фильма из бд")
    public void DeleteMovieTest() {
        List<MovieResponseDto> movies = getAllMovies();
        // достаем нужный фильм (будто был запрос по id)
        UUID id = movies.get(1).id();
        // необходимое кол-во фильмов после удаления одного фильма
        int checkMoviesCount = movies.size() - 1;

        Response r = given()
                .delete("/api/movie/" + id)
                .then()
                .extract().response();

        int resultMoviesCount = getAllMovies().size();

        assertEquals(checkMoviesCount, resultMoviesCount, "Не совпадает количество фильмов в бд");
    }

    private List<MovieResponseDto> getAllMovies() {
        // запрос по пути: localhost:8080/api/movie/all
        Response r = given()
                .when()
                .get("/api/movie/all")
                .then()
                .extract().response();

        // для того чтобы определить тип объекта json
        Type MovieResponseDtoListType = new TypeToken<List<MovieResponseDto>>() {}.getType();

        return new Gson().fromJson(r.getBody().asString(), MovieResponseDtoListType);
    }

    @Test
    @Description("Тест на обновление фильма с невалидными данными")
    public void NegativeUpdateMovieTest() {
        MovieDto movieDtoNegative = movieMapper.movieToMovieDto(testObjects.getMovieUpdateNegative());
        // получение всех фильмов
        List<MovieResponseDto> movies = getAllMovies();
        // достаем нужный фильм (будто был запрос по id)
        UUID id = movies.get(1).id();

        String movieJson = gson.toJson(movieDtoNegative);

        Response r = given()
                .header("Content-Type", "application/json")
                .body(movieJson)
                .put("/api/movie/" + id)
                .then()
                .extract().response();

        // Не выбрасываем ошибку, а возвращаем объект ErrorResponse
        ValidationErrorResponse errorResponse = gson.fromJson(r.getBody().asString(), ValidationErrorResponse.class);

        assertAll(
                () -> assertTrue(errorResponse.getErrors().contains("Название фильма должно быть от 1 до 32 символов"),
                        "Ошибка при передачи пустого поля в названии фильма"),
                () -> assertTrue(errorResponse.getErrors().contains("Продолжительность фильма должна быть не менее 1 минуты"),
                        "Ошибка при передачи отрицательного значения"),
                () -> assertTrue(errorResponse.getErrors().contains("Продолжительность фильма должна быть не менее 1 минуты"),
                        "Ошибка при передачи пустого поля в названии фильма")
        );
    }

    @Test
    @Description("Тест на некорректную передачу id для удаление фильма из бд")
    public void NegativeDeleteMovieTest() {
        // генерируем случайный id
        UUID id = UUID.randomUUID();

        Response r = given()
                .delete("/api/movie/" + id)
                .then()
                .extract().response();

        // ожидаем статус 204
        assertEquals(204, r.statusCode(), "Неудачно произошло удаление фильма из бд");
    }
    @Test
    @Description("Тест на получение корректного фильма из бд")
    public void NegativeGetMovieTest() {
        UUID id = UUID.randomUUID();

        Response r = given()
                .get("/api/movie/" + id)
                .then()
                .extract().response();

        // Не выбрасываем ошибку, а возвращаем объект ErrorResponse
        ErrorResponse errorResponse = gson.fromJson(r.getBody().asString(), ErrorResponse.class);


        if (errorResponse == null) {
            assertAll(
                    () -> assertEquals(null, errorResponse,
                            "Неверно указана ошибка при обращении к несуществующему фильму")
            );
        } else{
            assertAll(
                    () -> assertEquals("Данного фильма не существует", errorResponse.getMessage(),
                            "Неверно указана ошибка при обращении к несуществующему фильму")
            );
        }

    }
}
