package ru.croc.team4.administration.service;

import ru.croc.team4.administration.domain.Hall;
import ru.croc.team4.administration.repository.HallRepository;


import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

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
