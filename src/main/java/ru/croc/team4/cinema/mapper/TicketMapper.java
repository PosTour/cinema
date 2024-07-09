package ru.croc.team4.cinema.mapper;

import ru.croc.team4.cinema.domain.Ticket;
import ru.croc.team4.cinema.dto.TicketDto;
import java.util.List;

public interface TicketMapper {
    TicketDto ticketToTicketDto(Ticket ticket);

    List<TicketDto> ticketsToTicketDtos(List<Ticket> tickets);
}
