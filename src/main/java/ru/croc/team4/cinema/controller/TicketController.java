package ru.croc.team4.cinema.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.croc.team4.cinema.domain.Place;
import ru.croc.team4.cinema.dto.TicketClientDto;
import ru.croc.team4.cinema.dto.TicketDto;
import ru.croc.team4.cinema.dto.TicketOutputDto;
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

    @GetMapping("/{chatId}")
    public ResponseEntity<List<TicketDto>> getTicketsId(@PathVariable Long chatId) {
        List<TicketDto> tickets = ticketService.getTicketsByChatId(chatId);
        return ResponseEntity.ok(tickets);
    }

    @GetMapping("findByChatId/{id}")
    public ResponseEntity<List<TicketOutputDto>> getTicketByChatId(@PathVariable Long chatId) {
        List<TicketOutputDto> tickets = ticketService.getTicketsByChatId(chatId);
        return ResponseEntity.ok(tickets);
    }

    @PostMapping
    public ResponseEntity<TicketDto> createTicket(@RequestBody TicketDto ticketDto) {
        return ResponseEntity.ok(ticketService.createTicket(ticketDto));
    }

    @PutMapping("/{bCode}/{status}")
    public ResponseEntity<TicketDto> updateTicket(@PathVariable String bCode, @PathVariable Place.Status status) {
        TicketDto ticketDto = ticketService.updateTicket(bCode, status);
        return ResponseEntity.ok(ticketDto);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteTicket(@RequestBody TicketClientDto ticketClientDto) {
        ticketService.deleteTicket(ticketClientDto);
        return ResponseEntity.noContent().build();
    }
}
