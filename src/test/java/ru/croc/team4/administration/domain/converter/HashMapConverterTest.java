package ru.croc.team4.administration.domain.converter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.croc.team4.cinema.domain.Category;
import ru.croc.team4.cinema.domain.converter.HashMapConverter;
import scala.Int;

import java.util.HashMap;
import java.util.Map;

public class HashMapConverterTest {

    @Test
    public void testConvertMapToJson() {
        Map<Map<Integer, Integer>, Category> map = new HashMap<>();

        Map<Integer, Integer> map1 = new HashMap<>();
        map1.put(1,1);
        Map<Integer, Integer> map2 = new HashMap<>();
        map2.put(1,2);

        map.put(map1, Category.BAD);
        map.put(map2, Category.GOOD);


        HashMapConverter converter = new HashMapConverter();
        String text = converter.convertToDatabaseColumn(map);
        Assertions.assertEquals(text, "{\"{1=1}\":\"BAD\",\"{1=2}\":\"GOOD\"}");


    }



    @Test
    public void testConvertJsonToMap() {
        String json = "{\"{1=1}\":\"BAD\",\"{1=2}\":\"GOOD\"}";

        Map<Map<Integer, Integer>, Category> myMap = new HashMap<>();
        Map<Integer, Integer> map1 = new HashMap<>();
        map1.put(1,1);
        Map<Integer, Integer> map2 = new HashMap<>();
        map2.put(1,2);

        myMap.put(map1, Category.BAD);
        myMap.put(map2, Category.GOOD);

        HashMapConverter converter = new HashMapConverter();
        Map<Map<Integer, Integer>, Category> map = converter.convertToEntityAttribute(json);
        Assertions.assertEquals(map, myMap);
    }
}
