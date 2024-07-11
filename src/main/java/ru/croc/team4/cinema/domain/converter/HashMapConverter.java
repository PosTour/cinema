package ru.croc.team4.cinema.domain.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import ru.croc.team4.cinema.domain.Category;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Converter
public class HashMapConverter implements AttributeConverter<Map<Map<Integer, Integer>, Category>, String> {

    @Override
    public String convertToDatabaseColumn(Map<Map<Integer, Integer>, Category> customerInfo) {

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
    public Map<Map<Integer, Integer>, Category> convertToEntityAttribute(String customerInfoJSON) {

        ObjectMapper objectMapper = new ObjectMapper();
        Map<Map<Integer, Integer>, Category> customerInfo = null;

        try {
            customerInfo = objectMapper.readValue(customerInfoJSON,
                    new TypeReference<Map<Map<Integer, Integer>, Category>>() {});
        } catch (final IOException e) {
            // Добавим логирование ошибки
//            logger.error("JSON reading error: {}", e.getMessage(), e);
        }

        return customerInfo;
    }

}