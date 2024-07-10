package ru.croc.team4.cinema.dto;

import java.util.Date;
import java.sql.Timestamp;
import java.util.UUID;

public record AuditDto(UUID changeId, String operationType, String entity, Date changeTime, String changes) {
}
