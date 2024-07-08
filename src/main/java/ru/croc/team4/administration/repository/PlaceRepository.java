package ru.croc.team4.administration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.croc.team4.administration.domain.Place;

public interface PlaceRepository extends JpaRepository<Place, Integer> {
}
