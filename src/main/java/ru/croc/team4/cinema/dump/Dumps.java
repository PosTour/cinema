package ru.croc.team4.cinema.dump;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.croc.team4.cinema.domain.User;
import ru.croc.team4.cinema.repository.*;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.List;

@AllArgsConstructor
@Service
public class Dumps {

    private final HallRepository hallRepository;
    private final MovieRepository movieRepository;
    private final PlaceRepository placeRepository;
    private final RowRepository rowRepository;
    private final SessionRepository sessionRepository;
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;


    @Scheduled(cron = "0 0 * * 5")
    public void autoDump(){

        Instant now = Instant.now();
        String timestamp = DateTimeFormatter.ofPattern("").format(now);
        timestamp = "user" + timestamp + ".json";
        List<User> users = userRepository.findAll();
        try {
            objectMapper.writeValue(new File(timestamp),users);
        } catch (IOException e) {
            e.printStackTrace();
        }
        movieRepository.findAll();
        placeRepository.findAll();
        hallRepository.findAll();
        rowRepository.findAll();
        sessionRepository.findAll();
        ticketRepository.findAll();



    }
}

