package ru.croc.team4.cinema.dto;

import ru.croc.team4.cinema.domain.Place;
import ru.croc.team4.cinema.domain.Row;

import java.util.UUID;

public record PlaceDto(UUID id, Place.Status status, String placeNumber, boolean occupied, Row row) {
}
