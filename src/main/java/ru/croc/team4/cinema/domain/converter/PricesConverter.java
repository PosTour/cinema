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
public class PricesConverter implements AttributeConverter<Map<Category, Integer>, String> {

    @Override
    public String convertToDatabaseColumn(Map<Category, Integer> customerInfo) {

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
    public Map<Category, Integer> convertToEntityAttribute(String customerInfoJSON) {

        ObjectMapper objectMapper = new ObjectMapper();
        Map<Category, Integer> customerInfo = null;
        try {
            customerInfo = objectMapper.readValue(customerInfoJSON,
                    new TypeReference<HashMap<Category, Integer>>() {});
        } catch (final IOException e) {
            //logger.error("JSON reading error", e);
            // You should add logger
            //
        }

        return customerInfo;
    }
}
