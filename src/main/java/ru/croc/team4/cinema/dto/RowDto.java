package ru.croc.team4.cinema.dto;

import ru.croc.team4.cinema.domain.Session;

import java.util.UUID;

public record RowDto(UUID id, String rowNumber, Session session) {
}
