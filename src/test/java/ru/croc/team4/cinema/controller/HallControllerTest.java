package ru.croc.team4.cinema.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class HallControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Test get hall")
    public void getHallTest() throws Exception {
        String id = """
                {
                	"serialVersionUID": "",
                	"mostSigBits": "",
                	"leastSigBits": "",
                	"jla": {},
                	"NIBBLES": ""
                }""";

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
