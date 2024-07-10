package ru.croc.team4.cinema.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import ru.croc.team4.cinema.domain.Hall;
import ru.croc.team4.cinema.repository.HallRepository;
import ru.croc.team4.cinema.testObjects;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class HallControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private HallRepository hallRepository;

    @BeforeEach
    public void setup() {
        Hall hall = testObjects.getHall();
        hallRepository.save(hall);
    }

    @AfterEach
    public void cleanup() {
        hallRepository.deleteAllInBatch();
    }


    @Test
    @DisplayName("Test get halls")
    public void getHallsTest() throws Exception {
        mockMvc.perform(get("/api/hall/all"))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
