package ru.croc.team4.cinema.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.croc.team4.cinema.dto.MovieDto;
import ru.croc.team4.cinema.mapper.MovieMapper;
import ru.croc.team4.cinema.mapper.MovieMapperImpl;
import ru.croc.team4.cinema.testObjects;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Test create movie")
    public void createMovieTest() throws Exception {

        MovieMapper movieMapper = new MovieMapperImpl();
        MovieDto movieDto = movieMapper.movieToMovieDto(testObjects.getMovie());

        ObjectMapper oMapper = new ObjectMapper();
        String json = oMapper.writeValueAsString(movieDto);

        String id = "07c9903b-f2ba-42de-84ba-21896e514f83";

        mockMvc.perform(post("/api/movie/{id}", id)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("Test delete movie")
    public void deleteMovieTest() throws Exception {
        String id = """
                {
                	"serialVersionUID": "",
                	"mostSigBits": "",
                	"leastSigBits": "",
                	"jla": {},
                	"NIBBLES": ""
                }""";

        mockMvc.perform(delete("/api/movie/{id}", id))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("Test get movie")
    public void getMovieTest() throws Exception {
        String id = """
                {
                	"serialVersionUID": "",
                	"mostSigBits": "",
                	"leastSigBits": "",
                	"jla": {},
                	"NIBBLES": ""
                }""";

        mockMvc.perform(get("/api/movie/{id}", id))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("Test update movie")
    public void updateMovieTest() throws Exception {
        String id = """
                {
                	"serialVersionUID": "",
                	"mostSigBits": "",
                	"leastSigBits": "",
                	"jla": {},
                	"NIBBLES": ""
                }""";
        String movieDto = """
                {
                	"title": "",
                	"durationInMinutes": "",
                	"description": ""
                }""";

        mockMvc.perform(put("/api/movie/{id}", id)
                        .content(movieDto)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
