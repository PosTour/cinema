package ru.croc.team4.administration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.croc.team4.administration.domain.Place;
import ru.croc.team4.administration.domain.Row;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PlaceRepository extends JpaRepository<Place, Integer> {
    List<Place> findAllByRow(Row row);
    Optional<Place> findByPlaceId(UUID placeId);
}
