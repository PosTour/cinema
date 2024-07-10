package ru.croc.team4.cinema.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import ru.croc.team4.cinema.dto.TicketDto;
import ru.croc.team4.cinema.mapper.TicketMapper;
import ru.croc.team4.cinema.mapper.TicketMapperImpl;
import ru.croc.team4.cinema.testObjects;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class TicketControllerTest {

    @Autowired
    private MockMvc mockMvc;

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
    @DisplayName("Test delete ticket")
    public void deleteTicketTest() throws Exception {
        String ticketDto = """
                {
                	"user": {},
                	"session": {},
                	"place": {}
                }""";

        mockMvc.perform(delete("/api/ticket")
                        .content(ticketDto)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("Test get tickets")
    public void getTicketsTest() throws Exception {
        mockMvc.perform(get("/api/ticket/all"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("Test get tickets id")
    public void getTicketsIdTest() throws Exception {
        String id = """
                {
                	"serialVersionUID": "",
                	"mostSigBits": "",
                	"leastSigBits": "",
                	"jla": {},
                	"NIBBLES": ""
                }""";

        mockMvc.perform(get("/api/ticket/{id}", id))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
