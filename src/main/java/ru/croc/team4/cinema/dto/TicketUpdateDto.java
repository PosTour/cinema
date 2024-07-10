package ru.croc.team4.cinema.dto;

public record TicketUpdateDto(String bookingCode, long chatId, String newStatus) {
}
