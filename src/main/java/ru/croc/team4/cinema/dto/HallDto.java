package ru.croc.team4.cinema.dto;

import java.util.Map;

public record HallDto (String name, Map<String, String> seats){
}
