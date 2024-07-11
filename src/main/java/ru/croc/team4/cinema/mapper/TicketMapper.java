package ru.croc.team4.cinema.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.croc.team4.cinema.domain.Ticket;
import ru.croc.team4.cinema.dto.TicketDto;
import ru.croc.team4.cinema.dto.TicketOutputDto;

import java.util.List;

@Mapper
public interface TicketMapper {

    TicketDto ticketToTicketDto(Ticket ticket);

    Ticket ticketDtoToTicket(TicketDto ticketDto);

    List<TicketDto> ticketsToTicketDtos(List<Ticket> tickets);
    List<TicketOutputDto> ticketsToTicketOutputDtos(List<Ticket> tickets);

}
