package ru.croc.team4.administration.domain.converter;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.croc.team4.cinema.domain.Category;
import ru.croc.team4.cinema.domain.converter.HashMapConverter;
import scala.Int;

import java.util.HashMap;
import java.util.Map;

@ActiveProfiles("test")
@Epic("MapToJson and JsonTOMap test")
@Feature("Integrate tests")
public class HashMapConverterTest {

    @Test
    @Description("Check ConvertMapToJson")
    public void testConvertMapToJson() {
        Map<Integer, Map<Integer, Category>> map = new HashMap<>();

        Map<Integer, Category> map1 = new HashMap<>();
        map1.put(1,Category.BAD);
        Map<Integer, Category> map2 = new HashMap<>();
        map1.put(2,Category.GOOD);

        map.put(1, map1);
        //map.put(1, map2);


        HashMapConverter converter = new HashMapConverter();
        String text = converter.convertToDatabaseColumn(map);
        Assertions.assertEquals(text, "{\"1\":{\"1\":\"BAD\",\"2\":\"GOOD\"}}");


    }

    @Test
    @Description("Check ConvertJsonToMap")
    public void testConvertJsonToMap() {
        String json = "{\"1\":{\"1\":\"BAD\",\"2\":\"GOOD\"}}";

        Map<Integer, Map<Integer, Category>> myMap = new HashMap<>();
        Map<Integer, Category> map1 = new HashMap<>();
        map1.put(1,Category.BAD);
        map1.put(2,Category.GOOD);

        myMap.put(1, map1);

        HashMapConverter converter = new HashMapConverter();
        Map<Integer, Map<Integer, Category>> map = converter.convertToEntityAttribute(json);
        Assertions.assertEquals(myMap, map);
    }
}
