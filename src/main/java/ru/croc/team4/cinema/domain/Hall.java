package ru.croc.team4.cinema.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.UuidGenerator;
import ru.croc.team4.cinema.domain.converter.HashMapConverter;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Entity
@Getter
@Setter
@FieldNameConstants
@Builder
@AllArgsConstructor
public class Hall {

    @Id
    @UuidGenerator
    private UUID id;

    @Column(nullable = false, length = 32)
    private String name;

    @Column(nullable = false)
    @Convert(converter = HashMapConverter.class)
    private Map<String, String> seats = new HashMap<>();

    public Hall() {

    }
}
