package ru.croc.team4.cinema.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.croc.team4.cinema.dto.AuditDto;
import ru.croc.team4.cinema.dto.TicketUpdateDto;

@Component
public class KafkaSenderServiceImpl implements KafkaSenderService {

    private final KafkaTemplate<String,String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public KafkaSenderServiceImpl(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public void sendToAudit(AuditDto auditDto) {
        try {
        String msg = objectMapper.writeValueAsString(auditDto);
        kafkaTemplate.send("administration}", msg);
    } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void sendToBot(TicketUpdateDto ticketUpdateDto) {
        try {
            String msg = objectMapper.writeValueAsString(ticketUpdateDto);
            kafkaTemplate.send("tg-bot", msg);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }

}
