package ru.croc.team4.administration.dto;

import jakarta.persistence.Convert;
import lombok.Getter;
import lombok.Setter;
import ru.croc.team4.administration.domain.converter.HashMapConverter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class HallDto {

    private String name;

    @Convert(converter = HashMapConverter.class)
    private Map<Integer, Integer> seats = new HashMap<>();
}
