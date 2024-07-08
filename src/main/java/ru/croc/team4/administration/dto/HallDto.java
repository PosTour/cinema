package ru.croc.team4.administration.dto;

import java.util.Map;

public record HallDto (String name, Map<Integer, Integer> seats){
}
