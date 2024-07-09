package ru.croc.team4.cinema.service;

import org.springframework.stereotype.Service;
import ru.croc.team4.cinema.domain.Hall;
import ru.croc.team4.cinema.repository.HallRepository;


import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
public class HallServiceImpl implements HallService {

    private final HallRepository hallRepository;

    public HallServiceImpl(HallRepository hallRepository) {
        this.hallRepository = hallRepository;
    }

    @Override
    public Optional<Hall> findHallById(UUID hallId) {
        return hallRepository.findById(hallId);
    }

    @Override
    public void UpdateHall(UUID id, String name, int capacity, Map<Integer, Integer> seats) {
        var hall = hallRepository.findById(id);
        if (hall.isPresent()) {
            hall.get().setName(name);
            hall.get().setCapacity(capacity);
            hall.get().setSeats(seats);
        } else throw new NoSuchElementException("Hall isn't in db");
    }
}
