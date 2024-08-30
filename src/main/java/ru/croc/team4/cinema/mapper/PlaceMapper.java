package ru.croc.team4.cinema.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.croc.team4.cinema.domain.Place;
import ru.croc.team4.cinema.dto.PlaceDto;
import ru.croc.team4.cinema.service.RowServiceImpl;

import java.util.List;

@Mapper
public interface PlaceMapper {
    @Mapping(target = "status", expression = "java(place.getStatus().lable)")
    @Mapping(target = "type", expression = "java(place.getType().getRussianName())")
    @Mapping(target = "rowId", expression = "java(place.getRow().getId())")
    PlaceDto placeToPlaceDto(Place place);

    @Mapping(target = "row", expression = "java(rowServiceImpl.getRowById(placeDto.rowId()))")
    @Mapping(target = "status", expression = "java(Place.Status.getStatusByString(placeDto.status()))")
    Place placeDtoToPlace(PlaceDto placeDto, @Context RowServiceImpl rowServiceImpl);
    List<PlaceDto> placeListToPlaceDtoList(List<Place> placeList);
}
