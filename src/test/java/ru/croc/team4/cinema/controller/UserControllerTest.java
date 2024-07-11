package ru.croc.team4.cinema.controller;

import com.google.gson.Gson;
import io.qameta.allure.Description;
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
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.shaded.com.google.common.reflect.TypeToken;
import ru.croc.team4.cinema.domain.User;
import ru.croc.team4.cinema.dto.MovieResponseDto;
import ru.croc.team4.cinema.dto.UserDto;
import ru.croc.team4.cinema.mapper.UserMapper;
import ru.croc.team4.cinema.mapper.UserMapperImpl;
import ru.croc.team4.cinema.repository.UserRepository;
import ru.croc.team4.cinema.testObjects;
import utils.ReadProperties;

import java.lang.reflect.Type;
import java.util.List;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserControllerTest {
    @Autowired
    private UserRepository userRepository;

    @LocalServerPort
    private Integer port;

    private final Gson gson = new Gson();

    private final UserMapper userMapper;

    public UserControllerTest() {
        this.userMapper = new UserMapperImpl();
    }

    @BeforeEach
    public void setup() {
        // создаем настройки
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
        RestAssured.useRelaxedHTTPSValidation();

        // для того чтобы не засорять консоль
        if(Boolean.valueOf(String.valueOf(ReadProperties.propertiesRead().get("extended.log")))) RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

        User user = testObjects.getUser();
        User user2 = testObjects.getUser2();
        userRepository.save(user);
        userRepository.save(user2);
    }

    @AfterEach
    public void clean() {
        userRepository.deleteAllInBatch();
    }

    @Test
    @Description("Тест по проверке всех пользователей")
    public void getAllUsersTest() {
        List<UserDto> users = getAllUsers();

        assertEquals(2, users.size(), "Неверное количество фильмов в бд");
    }

    @Test
    @Description("Тест по проверке получения пользователя по номеру телефона")
    public void GetUserbyPhone() {
        List<UserDto> users = getAllUsers();

        long id = users.get(0).chatId();
    }

    private List<UserDto> getAllUsers() {
        // запрос по пути: localhost:8080/api/users/all
        Response r = given()
                .when()
                .get("/api/users/all")
                .then()
                .extract().response();

        // для того чтобы определить тип объекта json
        Type userDtoListType = new TypeToken<List<UserDto>>() {}.getType();

        return new Gson().fromJson(r.getBody().asString(), userDtoListType);
    }
}
