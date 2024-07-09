package ru.croc.team4.cinema.dto;

import java.util.Map;

public record HallDto (String name, int capacity, Map<Integer, Integer> seats){
}
