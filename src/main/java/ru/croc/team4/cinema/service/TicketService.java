package ru.croc.team4.cinema.service;

import ru.croc.team4.cinema.domain.Place;
import ru.croc.team4.cinema.dto.TicketDto;
import ru.croc.team4.cinema.dto.TicketOutputDto;

import java.util.List;
import java.util.UUID;

public interface TicketService {
    void deleteTicket(TicketDto ticketDto);

    TicketDto createTicket(TicketDto ticketDto);

    TicketDto updateTicket(String bCode, Place.Status status);

    List<TicketDto> getTicketsByUserId(UUID userId);

    List<TicketOutputDto> getTicketsByChatId(Long chatId);

    List<TicketDto> getAllTickets();
}
