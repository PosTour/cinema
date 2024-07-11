package ru.croc.team4.cinema.mapper;

import org.mapstruct.Mapper;
import ru.croc.team4.cinema.domain.Ticket;
import ru.croc.team4.cinema.dto.TicketClientDto;
import ru.croc.team4.cinema.dto.TicketDto;
import ru.croc.team4.cinema.dto.TicketOutputDto;

import java.util.List;

@Mapper
public interface TicketMapper {

    TicketDto ticketToTicketDto(Ticket ticket);

    Ticket ticketClientToTicket(TicketClientDto ticketClientDto);

    List<TicketDto> ticketsToTicketDtos(List<Ticket> tickets);

    List<TicketOutputDto> ticketsToTicketOutputDtos(List<Ticket> tickets);
}
