package ru.croc.team4.cinema.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.croc.team4.cinema.dto.TicketDto;
import ru.croc.team4.cinema.mapper.TicketMapper;
import ru.croc.team4.cinema.mapper.TicketMapperImpl;
import ru.croc.team4.cinema.service.TicketService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/ticket")
public class TicketController {

    private final TicketService ticketService;
    private final TicketMapper ticketMapper;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
        this.ticketMapper = new TicketMapperImpl();
    }

    @GetMapping("/all")
    public ResponseEntity<List<TicketDto>> getTickets() {
        List<TicketDto> tickets = ticketService.getAllTickets();
        return ResponseEntity.ok(tickets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<TicketDto>> getTicketsId(@PathVariable UUID id) {
        List<TicketDto> tickets = ticketService.getTicketsByUserId(id);
        return ResponseEntity.ok(tickets);
    }

    @PostMapping
    public ResponseEntity<TicketDto> createTicket(@RequestBody TicketDto ticketDto) {
        return ResponseEntity.ok(ticketService.createTicket(ticketDto));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteTicket(@RequestBody TicketDto ticketDto) {
        ticketService.deleteTicket(ticketDto);
        return ResponseEntity.noContent().build();
    }
}
