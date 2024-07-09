package ru.croc.team4.cinema;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
class AdministrationApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
	void contextLoads() {
	}

    @Test
    @DisplayName("Test create ticket")
    public void createTicketTest() throws Exception {
        String ticketDto = """
                {
                	"user": {"id": "07c9903b-f2ba-42de-84ba-21896e514f83", "phone": "123456789"},
                	"session": {"": ""},
                	"place": {"id": "07c9903b-f2ba-42de-84ba-21896e514f83", "placeNumber": "12b", "isOccupied": "true", "row": {}}
                }""";

        mockMvc.perform(post("/api/ticket")
                        .content(ticketDto)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("Test create movie")
    public void createMovieTest() throws Exception {
        String movieDto = """
                {
                	"title": "1+1",
                	"durationInMinutes": "122",
                	"description": "Good film"
                }""";
        String id = "07c9903b-f2ba-42de-84ba-21896e514f83";

        mockMvc.perform(post("/api/movie/{id}", id)
                        .content(movieDto)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
