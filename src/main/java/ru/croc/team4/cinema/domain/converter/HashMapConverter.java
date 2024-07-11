package ru.croc.team4.cinema.domain.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import ru.croc.team4.cinema.domain.Category;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Converter
public class HashMapConverter implements AttributeConverter<Map<Integer, Map<Integer, Category>>, String> {

    @Override
    public String convertToDatabaseColumn(Map<Integer, Map<Integer, Category>> customerInfo) {

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
    public Map<Integer, Map<Integer, Category>> convertToEntityAttribute(String customerInfoJSON) {

        ObjectMapper objectMapper = new ObjectMapper();

        Category customerInfo2 = null;

        Map<Map<Integer, Integer>, Category> map = new HashMap<>();

        String[] temp;
        String temp2;
        String temp3;
        String[] temp4;

        String[] list = customerInfoJSON.split(",");
        list[0] = list[0].substring(1);
        list[list.length - 1] = list[list.length - 1].substring(0, list[list.length - 1].length() - 1);
        for (int i = 0; i < list.length; ++i) {
            Map<Integer, Integer> customerInfo = new HashMap<>();
            try {

                temp = list[i].split(":");
                temp2 = temp[1].substring(1, temp[1].length() - 1);
                customerInfo2 = Category.valueOf(temp2);

                temp3 = temp[0].substring(2, temp[0].length() - 2);
                temp4 = temp3.split("=");
                customerInfo.put(Integer.valueOf(temp4[0]), Integer.valueOf(temp4[1]));

                map.put(customerInfo, customerInfo2);

            } catch (final Exception e) {
                // Добавим логирование ошибки
                //logger.error("JSON reading error: {}", e.getMessage(), e);
            }

        }


        return map;
    }

}