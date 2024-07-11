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
        Map<Integer, Map<Integer, Category>> map = new HashMap<>();

        Map<Integer, Category> map1 = new HashMap<>();
        map1.put(1,Category.BAD);
        Map<Integer, Category> map2 = new HashMap<>();
        map2.put(2,Category.GOOD);

        map.put(1, map1);
        map.put(1, map2);


        HashMapConverter converter = new HashMapConverter();
        String text = converter.convertToDatabaseColumn(map);
        Assertions.assertEquals(text, "{\"{1=1}\":\"BAD\",\"{1=2}\":\"GOOD\"}");


    }



    @Test
    public void testConvertJsonToMap() {
        String json = "{\"{1=1}\":\"Боковушка\",\"{1=2}\":\"Не до поцелуев\"}";

        Map<Map<Integer, Integer>, Category> myMap = new HashMap<>();
        Map<Integer, Integer> map1 = new HashMap<>();
        map1.put(1,1);
        Map<Integer, Integer> map2 = new HashMap<>();
        map2.put(1,2);

        myMap.put(map1, Category.BAD);
        myMap.put(map2, Category.GOOD);

        HashMapConverter converter = new HashMapConverter();
        Map<Integer, Map<Integer, Category>> map = converter.convertToEntityAttribute(json);
        Assertions.assertEquals(myMap, map);
    }
}
