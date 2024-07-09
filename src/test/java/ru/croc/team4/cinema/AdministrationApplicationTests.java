package ru.croc.team4.cinema;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import ru.croc.team4.cinema.dto.MovieDto;
import ru.croc.team4.cinema.dto.TicketDto;
import ru.croc.team4.cinema.mapper.MovieMapper;
import ru.croc.team4.cinema.mapper.MovieMapperImpl;
import ru.croc.team4.cinema.mapper.TicketMapper;
import ru.croc.team4.cinema.mapper.TicketMapperImpl;


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

    AdministrationApplicationTests() throws JsonProcessingException {
    }

    @Test
	void contextLoads() {
	}





    @Test
    @DisplayName("Test create ticket")
    public void createTicketTest() throws Exception {

        TicketMapper tickitMapper = new TicketMapperImpl();
        TicketDto ticketDto = tickitMapper.ticketToTicketDto(testObjects.getTicket());

        ObjectMapper oMapper = new ObjectMapper();
        String json = oMapper.writeValueAsString(ticketDto);

        mockMvc.perform(post("/api/ticket")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

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
}
