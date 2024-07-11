package ru.croc.team4.cinema.domain.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Converter
public class HashMapConverter implements AttributeConverter<Map<Map<Integer, Integer>, String>, String> {

    @Override
    public String convertToDatabaseColumn(Map<Map<Integer, Integer>, String> customerInfo) {

        ObjectMapper objectMapper = new ObjectMapper();
        String customerInfoJson = null;
        try {
            customerInfoJson = objectMapper.writeValueAsString(customerInfo);
        } catch (final JsonProcessingException e) {
            //logger.error("JSON writing error", e);
        }

        return customerInfoJson;
    }



    @Override
    public Map<Map<Integer, Integer>, String> convertToEntityAttribute(String customerInfoJSON) {

        ObjectMapper objectMapper = new ObjectMapper();
        Map<Map<Integer, Integer>, String> customerInfo = null;
        try {
            customerInfo = objectMapper.readValue(customerInfoJSON,
                    new TypeReference<Map<Map<Integer, Integer>, String>>() {});
        } catch (final IOException e) {
            //logger.error("JSON reading error", e);
            // You should add logger
            //
        }

        return customerInfo;
    }
}