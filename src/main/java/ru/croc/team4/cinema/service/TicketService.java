package ru.croc.team4.cinema.service;

import ru.croc.team4.cinema.dto.TicketDto;

import java.util.List;
import java.util.UUID;

public interface TicketService {
    void deleteTicket(TicketDto ticketDto);

    TicketDto createTicket(TicketDto ticketDto);

    List<TicketDto> getTicketsByUserId(UUID userId);

    List<TicketDto> getAllTickets();
}
