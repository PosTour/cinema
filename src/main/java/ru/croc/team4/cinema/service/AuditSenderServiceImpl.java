package ru.croc.team4.cinema.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.croc.team4.cinema.dto.AuditDto;


@Service
public class AuditSenderServiceImpl implements AuditSenderService{

    private final KafkaTemplate<String,String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    private final String topic = "${spring.kafka.producer.topic}";

    public AuditSenderServiceImpl(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public void sendToAudit(AuditDto auditDto) {
        try {
        String msg = objectMapper.writeValueAsString(auditDto);
        kafkaTemplate.send(topic, msg);
    } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }
}
