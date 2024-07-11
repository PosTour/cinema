package ru.croc.team4.cinema.dto;

import java.util.UUID;

public record TicketClientDto(Long chatId, String bookingCode, UUID sessionId, UUID placeId) {
}