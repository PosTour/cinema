package ru.croc.team4.cinema.dto;

import java.util.UUID;

public record TicketDto(String bookingCode, UUID sessionId, UUID placeId) {
}
