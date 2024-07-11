package ru.croc.team4.cinema.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.croc.team4.cinema.domain.Session;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class SessionControllerTest {



    @Test
    @DisplayName("Тест на создание сеанса")
    public void createSessionTest() throws Exception {

    }

    @Test
    @DisplayName("Тест на удаление сеанса")
    public void deleteSessionTest() throws Exception {

    }

    @Test
    @DisplayName("Тест получения всех сеансов")
    public void getMoviesTest() throws Exception {

    }

    @Test
    @DisplayName("Тест получения сеанса по Id")
    public void getSessionByIdTest() throws Exception {

    }

    @Test
    @DisplayName("Тест получения сеанса по фильму")
    public void getSessionsByMovieTest() throws Exception {

    }

    @Test
    @DisplayName("Тест на обновления сеанса")
    public void updateSessionTest() throws Exception {

    }

    private List<Session> getAllSessions() {

    }
}
