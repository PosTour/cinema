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
        Map<Integer, Integer> customerInfo = null;
        Category customerInfo2 = null;

        Map<Map<Integer, Integer>, Category> map = new HashMap<>();


        String temp;
        String temp2;
        String temp3;

        String[] list = customerInfoJSON.split(",");
        list[0] = list[0].substring(1);
        list[list.length - 1] = list[list.length - 1].substring(0, list[list.length - 1].length() - 1);
        for (int i = 0; i < list.length; ++i) {
            //try {

                //temp = list[i].split(":")[1].substring(1, list[i].split(":")[1].length() - 1);
                //customerInfo2 = Category.getStatusByString(temp);


                //temp2 = list[i].split(":")[0];
                //temp3 = list[i].split(":")[0].substring(1, list[i].split(":")[1].length() - 1);
                //System.out.print("a");
                //customerInfo2 = Category.getStatusByString(temp);

                //map.put(customerInfo, customerInfo2);
            //} catch (final Exception e) {
                // Добавим логирование ошибки
                //logger.error("JSON reading error: {}", e.getMessage(), e);
            //}

        }


        return map;
    }

}