package ru.croc.team4.cinema.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import ru.croc.team4.cinema.domain.User;
import ru.croc.team4.cinema.repository.UserRepository;
import ru.croc.team4.cinema.testObjects;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserControllerTest {
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @BeforeEach
//    public void setup() {
//        User user = testObjects.getUser();
//        userRepository.save(user);
//    }
//
//    @AfterEach
//    public void clean() {
//        userRepository.deleteAllInBatch();
//    }
//
//    @Test
//    @DisplayName("Тест по проверке всех пользователей")
//    public void getAllUsersTest() throws Exception {
//        mockMvc.perform(get("/api/users/all"))
//                .andExpect(status().isOk())
//                .andDo(print());
//    }
}
