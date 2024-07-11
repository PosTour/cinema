package ru.croc.team4.cinema.mapper;

import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
import org.mapstruct.Mapping;
import ru.croc.team4.cinema.domain.Place;
import ru.croc.team4.cinema.dto.PlaceDto;

import java.util.List;

@Mapper
public interface PlaceMapper {
    @Mapping(target = "status", expression = "java(place.getStatus().lable)")
        //TODO ckech work it or ot
    @Mapping(target = "type", expression = "java(\"expensive\")") //TODO edit it
    @Mapping(target = "rowId", expression = "java(place.getRow().getId())")
    PlaceDto placeToPlaceDto(Place place);
    Place placeDtoToPlace(PlaceDto placeDto);
    List<PlaceDto> placeListToPlaceDtoList(List<Place> placeList);
}
