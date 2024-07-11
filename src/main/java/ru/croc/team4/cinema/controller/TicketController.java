package ru.croc.team4.cinema.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.croc.team4.cinema.domain.Place;
import ru.croc.team4.cinema.domain.User;
import ru.croc.team4.cinema.dto.TicketClientDto;
import ru.croc.team4.cinema.dto.TicketDto;
import ru.croc.team4.cinema.dto.TicketOutputDto;
import ru.croc.team4.cinema.service.TicketService;
import ru.croc.team4.cinema.service.UserServiceImpl;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/ticket")
public class TicketController {

    private final TicketService ticketService;
    private final UserServiceImpl userServiceImpl;

    @Autowired
    public TicketController(TicketService ticketService, UserServiceImpl userServiceImpl) {
        this.ticketService = ticketService;
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/all")
    public ResponseEntity<List<TicketDto>> getTickets() {
        List<TicketDto> tickets = ticketService.getAllTickets();
        return ResponseEntity.ok(tickets);
    }

    @GetMapping("findByChatId/{chatId}")
    public ResponseEntity<List<TicketOutputDto>> getTicketByChatId(@PathVariable ("chatId") long chatId) {
        Optional<User> user = userServiceImpl.getUserByChatId(chatId);
        if (user.isPresent()) {
            List<TicketOutputDto> tickets = ticketService.getTicketsByUserId(user.get().getId());
            return ResponseEntity.ok(tickets);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Void> createTicket(@RequestBody TicketClientDto ticketClientDto) {
        ticketService.createTicket(ticketClientDto);
        return ResponseEntity.ok().build();
    }

    // через кафку
    @PutMapping("/{bCode}/{status}")
    public ResponseEntity<Void> updateTicket(@PathVariable("bCode") String bCode, @PathVariable ("status")Place.Status status) {
        ticketService.updateTicket(bCode, status);
        return ResponseEntity.ok().build();
    }

    // через кафку
    @DeleteMapping
    public ResponseEntity<Void> deleteTicket(@RequestBody TicketDto ticketDto) {
        ticketService.deleteTicket(ticketDto);
        return ResponseEntity.noContent().build();
    }
}
