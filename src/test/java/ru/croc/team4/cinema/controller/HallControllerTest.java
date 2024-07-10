package ru.croc.team4.cinema.controller;

import org.antlr.v4.runtime.misc.LogManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import ru.croc.team4.cinema.domain.Hall;
import ru.croc.team4.cinema.repository.HallRepository;
import ru.croc.team4.cinema.service.HallService;
import ru.croc.team4.cinema.service.HallServiceImpl;
import ru.croc.team4.cinema.testObjects;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
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
    @DisplayName("Test get hall")
    public void getHallTest() throws Exception {

        String id = "07c9903b-f2ba-42de-84ba-21896e514f83";
//                """
//                {
//                	"serialVersionUID": "",
//                	"mostSigBits": "",
//                	"leastSigBits": "",
//                	"jla": {},
//                	"NIBBLES": ""
//                }""";

        mockMvc.perform(get("/api/hall/{id}", id))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("Test update hall")
    public void updateHallTest() throws Exception {
        String id = """
                {
                	"serialVersionUID": "",
                	"mostSigBits": "",
                	"leastSigBits": "",
                	"jla": {},
                	"NIBBLES": ""
                }""";
        String hallDto = """
                {
                	"name": "",
                	"seats": {}
                }""";

        mockMvc.perform(put("/api/hall/{id}", id)
                        .content(hallDto)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
