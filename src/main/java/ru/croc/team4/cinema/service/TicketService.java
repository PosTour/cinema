package ru.croc.team4.cinema.service;

import ru.croc.team4.cinema.domain.Place;
import ru.croc.team4.cinema.dto.TicketClientDto;
import ru.croc.team4.cinema.dto.TicketDto;
import ru.croc.team4.cinema.dto.TicketOutputDto;

import java.util.List;
import java.util.UUID;

public interface TicketService {
    void deleteTicket(TicketDto ticketClientDto);

    void createTicket(TicketClientDto ticketClientDto);

    void updateTicket(String bCode, Place.Status status);

    List<TicketDto> getAllTickets();

    List<TicketOutputDto> getTicketsByUserId(UUID userId);
}
