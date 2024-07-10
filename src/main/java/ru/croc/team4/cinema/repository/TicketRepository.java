package ru.croc.team4.cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.croc.team4.cinema.domain.Ticket;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    List<Ticket> getTicketsByUserId(UUID userId);

    Optional<Ticket> getTicketByTicketId(UUID ticketId);

    Ticket getTicketByBookingCode(String bookingCode);

    @Query("DELETE FROM Ticket t WHERE t.user.id = :user_id and t.session.id = :session_id and t.place.id = :place_id")
    void deleteTicket(
            @Param("user_id") UUID user_id,
            @Param("session_id") UUID session_id,
            @Param("place_id") UUID place_id);
}