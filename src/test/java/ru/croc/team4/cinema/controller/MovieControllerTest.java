package ru.croc.team4.cinema.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
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
import org.springframework.test.web.servlet.RequestBuilder;
import org.testcontainers.shaded.com.google.common.reflect.TypeToken;
import ru.croc.team4.cinema.domain.Hall;
import ru.croc.team4.cinema.domain.Movie;
import ru.croc.team4.cinema.dto.MovieDto;
import ru.croc.team4.cinema.dto.MovieResponseDto;
import ru.croc.team4.cinema.mapper.MovieMapper;
import ru.croc.team4.cinema.mapper.MovieMapperImpl;
import ru.croc.team4.cinema.repository.HallRepository;
import ru.croc.team4.cinema.repository.MovieRepository;
import ru.croc.team4.cinema.testObjects;
import utils.ReadProperties;

import java.lang.reflect.Type;
import java.time.Duration;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
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
        Movie movie = testObjects.getMovie();
        Movie movie2 = testObjects.getMovie();
        movieRepository.save(movie);
        movieRepository.save(movie2);
    }

    @AfterEach
    public void cleanup() {
        movieRepository.deleteAllInBatch();
    }

    @Test
    @DisplayName("Test create movie")
    public void createMovieTest() throws Exception {

        MovieMapper movieMapper = new MovieMapperImpl();
        MovieDto movieDto = movieMapper.movieToMovieDto(testObjects.getMovie());

        String json = oMapper.writeValueAsString(movieDto);
        System.out.print(json);

        // нужно сделать MovieResponseDto
        MovieResponseDto movie = MovieResponseDto.builder()
                .id(UUID.fromString("07c9903b-f2ba-42de-84ba-21896e514f83"))
                .title("It")
                .durationInMinutes(122)
                .description("gret film")
                .build();

        String result = oMapper.writeValueAsString(movie);

        mockMvc.perform(post("/api/movie")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(result))
                .andDo(print());
    }

    @Test
    public void getMovieTest() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
        RestAssured.useRelaxedHTTPSValidation();

        if(Boolean.valueOf(String.valueOf(ReadProperties.propertiesRead().get("extended.log")))) RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

        Response r = given()
                .when()
                .get("/api/movie/all")
                .then()
                .extract().response();

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
