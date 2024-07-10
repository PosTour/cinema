package ru.croc.team4.cinema.dto;

import ru.croc.team4.cinema.domain.Place;
import ru.croc.team4.cinema.domain.Session;
import ru.croc.team4.cinema.domain.User;

public record TicketDto(User user, String bookingCode, Session session, Place place) {
}
