package ru.croc.team4.administration.service;

import ru.croc.team4.administration.domain.Hall;
import ru.croc.team4.administration.repository.HallRepository;


import java.util.Optional;
import java.util.UUID;

public class HallService {


    private HallRepository hallRepository;

    public Optional<Hall> findHall(UUID hallId) {
        return hallRepository.findById(hallId);
    }
}
