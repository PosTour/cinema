package ru.croc.team4.administration.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.croc.team4.administration.domain.Hall;
import ru.croc.team4.administration.repository.HallRepository;


import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

public class HallService {

    private HallRepository hallRepository;

    public Optional<Hall> findHallById(UUID hallId) {
        return hallRepository.findById(hallId);
    }

    public void UpdateHall(UUID id, String name, int capacity, Map<Integer, Integer> seats) {
        var hall = hallRepository.findById(id);
        if (hall.isPresent()) {
            hall.get().setName(name);
            hall.get().setCapacity(capacity);
            hall.get().setSeats(seats);

        } else throw new NoSuchElementException("Hall isn't in db");
    }
}
