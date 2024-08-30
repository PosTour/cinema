package ru.croc.team4.cinema.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.croc.team4.cinema.domain.Ticket;
import ru.croc.team4.cinema.dto.TicketClientDto;
import ru.croc.team4.cinema.dto.TicketDto;
import ru.croc.team4.cinema.dto.TicketOutputDto;
import ru.croc.team4.cinema.service.PlaceServiceImpl;
import ru.croc.team4.cinema.service.SessionServiceImpl;
import ru.croc.team4.cinema.service.UserServiceImpl;

import java.util.List;

@Mapper
public interface TicketMapper {
    @Mapping(target = "sessionId", expression = "java(ticket.getSession().getId())")
    @Mapping(target = "placeId", expression = "java(ticket.getPlace().getId())")
    TicketDto ticketToTicketDto(Ticket ticket);

    @Mapping(target = "session", expression = "java(sessionServiceImpl.findSession(ticketClientDto.sessionId()).get())")
    @Mapping(target = "place", expression = "java(placeServiceImpl.findById(ticketClientDto.placeId()).get())")
    @Mapping(target = "user", expression = "java(userServiceImpl.getUserByChatId(ticketClientDto.chatId()).get())")
    Ticket ticketClientToTicket(TicketClientDto ticketClientDto, @Context SessionServiceImpl sessionServiceImpl
            , @Context PlaceServiceImpl placeServiceImpl
            , @Context UserServiceImpl userServiceImpl);

    List<TicketDto> ticketsToTicketDtos(List<Ticket> tickets);

    @Mapping(target = "sessionId", expression = "java(ticket.getSession().getId())")
    @Mapping(target = "status", expression = "java(ticket.getPlace().getStatus().lable)")
    @Mapping(target = "rowNumber", expression = "java(ticket.getPlace().getRow().getRowNumber())")
    @Mapping(target = "placeNumber", expression = "java(ticket.getPlace().getPlaceNumber())")
    TicketOutputDto ticketToTicketOutputDto(Ticket ticket);

    List<TicketOutputDto> ticketsToTicketOutputDtos(List<Ticket> tickets);
}
