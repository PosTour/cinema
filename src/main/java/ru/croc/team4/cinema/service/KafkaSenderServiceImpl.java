package ru.croc.team4.cinema.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.croc.team4.cinema.dto.AuditDto;
import ru.croc.team4.cinema.dto.TicketUpdateDto;

@Service
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
        kafkaTemplate.send("${spring.kafka.producer.topic}", msg);
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
