package ru.croc.team4.cinema.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.croc.team4.cinema.domain.Hall;
import ru.croc.team4.cinema.dto.HallDto;
import ru.croc.team4.cinema.dto.MovieResponseDto;
import ru.croc.team4.cinema.mapper.HallMapper;
import ru.croc.team4.cinema.mapper.HallMapperImpl;
import ru.croc.team4.cinema.mapper.MovieMapperImpl;
import ru.croc.team4.cinema.repository.HallRepository;
import ru.croc.team4.cinema.repository.MovieRepository;


import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
public class HallServiceImpl implements HallService {

    private final HallRepository hallRepository;
    private final HallMapper hallMapper;

    @Autowired
    public HallServiceImpl(HallRepository hallRepository) {
        this.hallRepository = hallRepository;
        this.hallMapper = new HallMapperImpl();
    }


    @Override
    public Iterable<HallDto> findAllHalls() {
        return hallMapper.hallListToHallDto(hallRepository.findAll());
    }

    @Override
    public Optional<Hall> findHallById(UUID hallId) {
        return hallRepository.findById(hallId);
    }

    @Override
    public void UpdateHall(UUID id, String name, Map<String, String> seats) {
        var hall = hallRepository.findById(id);
        if (hall.isPresent()) {
            hall.get().setName(name);
            hall.get().setSeats(seats);
        } else throw new NoSuchElementException("Hall isn't in db");
    }
}
