package ru.croc.team4.cinema.service;

import ru.croc.team4.cinema.dto.TicketDto;
import java.util.UUID;

public interface TicketService {
    void deleteTicket(TicketDto ticketDto);

    TicketDto createTicket(TicketDto ticketDto);

    Iterable<TicketDto> getTicketsByUserId(UUID userId);

    Iterable<TicketDto> getAllTickets();
}
