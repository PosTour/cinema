package ru.croc.team4.cinema.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import ru.croc.team4.cinema.domain.Place;
import ru.croc.team4.cinema.domain.Row;
import ru.croc.team4.cinema.dto.PlaceDto;
import ru.croc.team4.cinema.repository.HallRepository;
import ru.croc.team4.cinema.repository.PlaceRepository;
import ru.croc.team4.cinema.repository.RowRepository;
import ru.croc.team4.cinema.service.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Mapper
public interface PlaceMapper {
    @Mapping(target = "status", expression = "java(place.getStatus().lable)")
        //TODO ckech work it or ot
    @Mapping(target = "type", expression = "java(\"expensive\")") //TODO edit it
    @Mapping(target = "rowId", expression = "java(place.getRow().getId())")
    PlaceDto placeToPlaceDto(Place place);

    @Mapping(target = "row", expression = "java(rowServiceImpl.getRowById(placeDto.rowId()))")
    @Mapping(target = "status", expression = "java(Place.Status.getStatusByString(placeDto.status()))")
    Place placeDtoToPlace(PlaceDto placeDto, @Context RowServiceImpl rowServiceImpl);
    List<PlaceDto> placeListToPlaceDtoList(List<Place> placeList);
}

//@Mappings({
//        @Mapping(target = "client", expression = "java(clientRepository.findById(bookingRequest.getClientId()).orElse(null))"),        @Mapping(target = "employee",expression = "java(doctorRepository.findById(bookingRequest.getDoctorId()).orElse(null))"),
//        @Mapping(target = "startTime", expression = "java(bookingRequest.getStartTime())"),        @Mapping(target = "endTime", expression = "java(bookingRequest.getEndTime())")
//})Reception toReceptionBookingRequest(ReceptionBookingRequest bookingRequest,
//                                      @Context ClientRepository clientRepository,                                    @Context DoctorRepository doctorRepository
//);
//default String getFullName(Reception reception){
//    Doctor doctor = reception.getEmployee();    return doctor.getSurname() + " " + doctor.getName()
//            + " " + doctor.getPatronymic();}