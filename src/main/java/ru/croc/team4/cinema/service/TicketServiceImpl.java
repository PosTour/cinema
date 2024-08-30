package ru.croc.team4.cinema.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.croc.team4.cinema.domain.Place;
import ru.croc.team4.cinema.domain.Ticket;
import ru.croc.team4.cinema.domain.User;
import ru.croc.team4.cinema.dto.TicketClientDto;
import ru.croc.team4.cinema.dto.TicketDto;
import ru.croc.team4.cinema.dto.TicketOutputDto;
import ru.croc.team4.cinema.dto.TicketUpdateDto;
import ru.croc.team4.cinema.mapper.TicketMapper;
import ru.croc.team4.cinema.mapper.TicketMapperImpl;
import ru.croc.team4.cinema.repository.TicketRepository;
import ru.croc.team4.cinema.repository.UserRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;
    private final KafkaSenderService kafkaSenderService;
    private final UserRepository userRepository;

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository, KafkaSenderService kafkaSenderService, UserRepository userRepository) {
        this.ticketRepository = ticketRepository;
        this.kafkaSenderService = kafkaSenderService;
        this.ticketMapper = new TicketMapperImpl();
        this.userRepository = userRepository;
    }

    @Override
    public void createTicket(TicketClientDto ticketClientDto) {
        Ticket ticket = ticketMapper.ticketClientToTicket(ticketClientDto);
        ticket.getPlace().setStatus(Place.Status.BOOKING);
        ticketRepository.save(ticket);
    }

    @Override
    public void updateTicket(String bCode, Place.Status status) {
        Ticket ticket = ticketRepository.getTicketByBookingCode(bCode);
        TicketUpdateDto ticketUpdateDto = new TicketUpdateDto(bCode, ticket.getUser().getChatId(), "PAID");
        kafkaSenderService.sendToBot(ticketUpdateDto);
        ticket.getPlace().setStatus(status);
        ticketMapper.ticketToTicketDto(ticketRepository.save(ticket));
    }

    public List<TicketOutputDto> getTicketsByUserId(UUID userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            List<Ticket> tickets = ticketRepository.getTicketsByUser(user.get());
            return ticketMapper.ticketsToTicketOutputDtos(tickets);
        }
        return Collections.emptyList();
    }

    @Override
    public List<TicketDto> getAllTickets() {
        List<Ticket> tickets = ticketRepository.findAll();
        return ticketMapper.ticketsToTicketDtos(tickets);
    }

    @Override
    public void deleteTicket(TicketDto ticketDto) {
        Ticket ticket = ticketRepository.getTicketByBookingCode(ticketDto.bookingCode());
        TicketUpdateDto ticketUpdateDto = new TicketUpdateDto(ticketDto.bookingCode(), ticket.getUser().getChatId(), "FREE");
        kafkaSenderService.sendToBot(ticketUpdateDto);
        ticket.getPlace().setStatus(Place.Status.FREE);
        ticketRepository.deleteTicket(ticket.getUser().getChatId(), ticket.getSession().getId(), ticket.getPlace().getId());
    }
}
