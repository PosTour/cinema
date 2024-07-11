package ru.croc.team4.administration.domain.converter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.croc.team4.cinema.domain.converter.HashMapConverter;

import java.util.HashMap;
import java.util.Map;

public class HashMapConverterTest {

    @Test
    public void testConvertMapToJson() {
        Map<Map<Integer, Integer>, String> map = new HashMap<>();

        map.put(new HashMap<>(1,1), "ex");
        map.put(new HashMap<>(1,2), "po");

        HashMapConverter converter = new HashMapConverter();
        String text = converter.convertToDatabaseColumn(map);
        Assertions.assertEquals(text, "{\"1\":3,\"2\":2}");


    }



    @Test
    public void testConvertJsonToMap() {
        String json = "{\"1\":3,\"2\":2}";

        Map<Map<Integer, Integer>, String> myMap = new HashMap<>();

        myMap.put(new HashMap<>(1,1), "ex");
        myMap.put(new HashMap<>(1,2), "po");

        HashMapConverter converter = new HashMapConverter();
        Map<Map<Integer, Integer>, String> map = converter.convertToEntityAttribute(json);
        Assertions.assertEquals(map, myMap);
    }
}
