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
import ru.croc.team4.cinema.dto.PlaceDto;
import ru.croc.team4.cinema.mapper.PlaceMapper;
import ru.croc.team4.cinema.mapper.PlaceMapperImpl;
import ru.croc.team4.cinema.testObjects;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class PlaceControllerTest {

    @Autowired
    private MockMvc mockMvc;


//    @Test
//    @DisplayName("Тест по созданию места")
//    public void createPlaceTest() throws Exception {
//        PlaceMapper placeMapper = new PlaceMapperImpl();
//        PlaceDto placeDto = placeMapper.placeToPlaceDto(testObjects.getPlace());
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        String json = objectMapper.writeValueAsString(placeDto);
//        System.out.print(testObjects.getPlace().getPlaceNumber());
//
//        mockMvc.perform(post("/api/place")
//                        .content(json)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andDo(print());
//    }
//
//    @Test
//    @DisplayName("Тест по получению всех мест")
//    public void getAllPlacesTest() throws Exception {
//
//
//        mockMvc.perform(get("/api/place"))
//                .andExpect(status().isOk())
//                .andDo(print());
//    }

}
