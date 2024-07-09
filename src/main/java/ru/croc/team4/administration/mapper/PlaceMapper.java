package ru.croc.team4.administration.mapper;

import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
import ru.croc.team4.administration.domain.Place;
import ru.croc.team4.administration.dto.PlaceDto;

import java.util.List;

@Mapper
public interface PlaceMapper {
    PlaceDto placeToPlaceDto(Place place);
    Place placeDtoToPlace(PlaceDto placeDto);
    List<PlaceDto> placeListToPlaceDtoList(List<Place> placeList);
}
