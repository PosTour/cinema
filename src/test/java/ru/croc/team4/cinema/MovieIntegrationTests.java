package ru.croc.team4.cinema;


import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import ru.croc.team4.cinema.domain.Movie;
import ru.croc.team4.cinema.service.MovieService;

import java.time.Duration;

import static io.restassured.RestAssured.given;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MovieIntegrationTests {
    @LocalServerPort
    private Integer port;

    @Autowired
    private MovieService movieService;

    @BeforeEach
    public void setup() {
        Movie movie = new Movie();
        movie.setTitle("The Godfather");
        movie.setDescription("descrption");
        movie.setDuration(Duration.ofMinutes(120));
        movie.
    }

    @AfterEach
    public void cleanup() {
        e.deleteAllInBatch();
        departmentRepository.deleteAllInBatch();
    }

    @Test
    @Description("SearchEmployeeController check by name")
    public void exampleSystemTest() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
        RestAssured.useRelaxedHTTPSValidation();

        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

        Response r = given()
                .queryParam("search", "Иванов Иван Иванович")
                .when()
                .get("/employee")
                .then()
                .extract().response();
        EmployeeDto resultObject = new Gson().fromJson(r.getBody().asString(), EmployeeDto.class);
        Assertions.assertEquals(HttpStatus.SC_OK, r.getStatusCode());
        Assertions.assertEquals("Основной департамент", resultObject.getDepartment());
        Assertions.assertEquals("Иван", resultObject.getFirstName());
        Assertions.assertEquals("Иванов", resultObject.getLastName());
        Assertions.assertEquals("79991234556", resultObject.getPhone());
    }
}
