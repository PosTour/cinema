package ru.croc.team4.cinema.mapper;

import org.mapstruct.Mapper;
import ru.croc.team4.cinema.domain.Ticket;
import ru.croc.team4.cinema.dto.TicketDto;
import java.util.List;

@Mapper
public interface TicketMapper {
    TicketDto ticketToTicketDto(Ticket ticket);

    List<TicketDto> ticketsToTicketDtos(List<Ticket> tickets);
}
