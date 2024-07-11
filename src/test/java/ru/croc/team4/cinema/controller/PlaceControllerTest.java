package ru.croc.team4.cinema.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import ru.croc.team4.cinema.domain.Movie;
import ru.croc.team4.cinema.domain.Row;
import ru.croc.team4.cinema.domain.Session;
import ru.croc.team4.cinema.dto.PlaceDto;
import ru.croc.team4.cinema.mapper.PlaceMapper;
import ru.croc.team4.cinema.mapper.PlaceMapperImpl;
import ru.croc.team4.cinema.repository.*;
import ru.croc.team4.cinema.testObjects;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class PlaceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private HallRepository hallRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private RowRepository rowRepository;
    @Autowired
    private PlaceRepository placeRepository;

    @BeforeEach
    public void setup() {
        Hall hall = testObjects.getHall();
        hallRepository.save(hall);


        Movie movie = testObjects.getMovie();
        movieRepository.save(movie);

        Session session = testObjects.getSession();
        sessionRepository.save(session);

        Row row = testObjects.getRow();
        rowRepository.save(row);
    }

    @AfterEach
    public void cleanup() {
        //placeRepository.deleteAllInBatch();
    }

    @Test
    @DisplayName("Тест по созданию места")
    public void createPlaceTest() throws Exception {
        PlaceMapper placeMapper = new PlaceMapperImpl();
        PlaceDto placeDto = placeMapper.placeToPlaceDto(testObjects.getPlace());

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(placeDto);
        System.out.print(json);

        mockMvc.perform(post("/api/place")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("Тест по получению всех мест")
    public void getAllPlacesTest()  {

    }

}
