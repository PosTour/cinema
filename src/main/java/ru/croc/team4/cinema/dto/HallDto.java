package ru.croc.team4.cinema.dto;

import java.util.Map;

public record HallDto (String name, Map<Integer, Integer> seats){
}
