package ru.croc.team4.cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.croc.team4.cinema.domain.Ticket;
import ru.croc.team4.cinema.dto.TicketDto;

import java.util.List;
import java.util.UUID;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    List<Ticket> getTicketsByUserId(UUID userId);

    void deleteTicketByTicketDto(TicketDto ticketDto);
}