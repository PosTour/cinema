package ru.croc.team4.administration.domain.converter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.croc.team4.cinema.domain.converter.HashMapConverter;

import java.util.HashMap;
import java.util.Map;

public class HashMapConverterTest {

    @Test
    public void testConvertMapToJson() {
        Map<Integer,Integer> map = new HashMap<>();

        map.put(1, 3);
        map.put(2, 2);

        HashMapConverter converter = new HashMapConverter();
        String text = converter.convertToDatabaseColumn(map);
        Assertions.assertEquals(text, "{\"1\":3,\"2\":2}");


    }



    @Test
    public void testConvertJsonToMap() {
        String json = "{\"1\":3,\"2\":2}";

        Map<Integer,Integer> myMap = new HashMap<>();

        myMap.put(1, 3);
        myMap.put(2, 2);

        HashMapConverter converter = new HashMapConverter();
        Map<Integer,Integer> map = converter.convertToEntityAttribute(json);
        Assertions.assertEquals(map, myMap);
    }
}
