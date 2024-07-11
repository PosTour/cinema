package ru.croc.team4.cinema.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.croc.team4.cinema.domain.Hall;
import ru.croc.team4.cinema.dto.AuditDto;
import ru.croc.team4.cinema.dto.HallDto;
import ru.croc.team4.cinema.dto.HallResponseDto;
import ru.croc.team4.cinema.mapper.HallMapper;
import ru.croc.team4.cinema.mapper.HallMapperImpl;
import ru.croc.team4.cinema.repository.HallRepository;


import java.util.*;

@Service
public class HallServiceImpl implements HallService {

    private final HallRepository hallRepository;
    private final HallMapper hallMapper;
    private final KafkaSenderService kafkaSenderService;

    @Autowired
    public HallServiceImpl(HallRepository hallRepository, KafkaSenderService kafkaSenderService) {
        this.hallRepository = hallRepository;
        this.hallMapper = new HallMapperImpl();
        this.kafkaSenderService = kafkaSenderService;
    }


    @Override
    public Iterable<HallResponseDto> findAllHalls() {
        return hallMapper.hallListToHallResponseDto(hallRepository.findAll());
    }

    @Override
    public Optional<Hall> findHallById(UUID hallId) {
        return hallRepository.findById(hallId);
    }

    @Override
    public HallResponseDto createHall(HallDto hallDto) {
        Hall hall = hallMapper.hallDtoToHall(hallDto);
        hallRepository.save(hall);
        HallResponseDto hallResponseDto = hallMapper.hallToHallResponseDto(hall);
        AuditDto auditDto = new AuditDto( hallResponseDto.id(), "create", "hall", new Date(), hall.toString());
//        kafkaSenderService.sendToAudit(auditDto);
        return hallResponseDto;
    }

    @Override
    public void UpdateHall(UUID id, String name, Map<Integer, Integer> seats) {
        var hall = hallRepository.findById(id);
        if (hall.isPresent()) {
            hall.get().setName(name);
            hall.get().setSeats(seats);
            hallRepository.save(hall.get());
        } else throw new NoSuchElementException("Hall isn't in db");
    }

    public Hall getHallByName(String name) {
        return hallRepository.findByName(name);
    }
}
