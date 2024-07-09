package ru.croc.team4.administration.dto;

import ru.croc.team4.administration.domain.Session;

import java.util.UUID;

public record RowDto(UUID id, int inSessionId, Session session) {
}
