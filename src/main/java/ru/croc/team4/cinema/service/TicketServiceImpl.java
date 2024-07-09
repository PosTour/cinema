package ru.croc.team4.cinema.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.croc.team4.cinema.domain.Ticket;
import ru.croc.team4.cinema.dto.TicketDto;
import ru.croc.team4.cinema.mapper.TicketMapper;
import ru.croc.team4.cinema.mapper.TicketMapperImpl;
import ru.croc.team4.cinema.repository.TicketRepository;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
        this.ticketMapper = new TicketMapperImpl();
    }

    @Override
    public TicketDto createTicket(TicketDto ticketDto) {
        Ticket ticketSaved = ticketRepository.save(new Ticket(ticketDto.user(), ticketDto.session(), ticketDto.place()));
        return ticketMapper.ticketToTicketDto(ticketSaved);
    }

    @Override
    public List<TicketDto> getTicketsByUserId(UUID userId) {
        List<Ticket> tickets = ticketRepository.getTicketsByUserId(userId).stream().toList();
        return ticketMapper.ticketsToTicketDtos(tickets);
    }

    @Override
    public List<TicketDto> getAllTickets() {
        List<Ticket> tickets = ticketRepository.findAll();
        return ticketMapper.ticketsToTicketDtos(tickets);
    }

    @Override
    public void deleteTicket(TicketDto ticketDto) {
        ticketRepository.deleteTicketByTicketDto(ticketDto);
    }
}