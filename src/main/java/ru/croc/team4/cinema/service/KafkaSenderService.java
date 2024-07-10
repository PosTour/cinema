package ru.croc.team4.cinema.service;

import ru.croc.team4.cinema.dto.AuditDto;
import ru.croc.team4.cinema.dto.TicketUpdateDto;

public interface KafkaSenderService {
    void sendToAudit (AuditDto auditDto);

    void sendToBot(TicketUpdateDto ticketUpdateDto);
}
