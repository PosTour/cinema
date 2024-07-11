package ru.croc.team4.cinema.dto;

import ru.croc.team4.cinema.domain.Place;
import ru.croc.team4.cinema.domain.Session;
import ru.croc.team4.cinema.domain.User;

import java.util.UUID;

public record TicketDto(String bookingCode, UUID sessionId, UUID placeId) {
}
