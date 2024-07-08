package ru.croc.team4.administration.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.UuidGenerator;
import ru.croc.team4.administration.domain.converter.HashMapConverter;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Entity
@Getter
@Setter
@FieldNameConstants
public class Hall {

    @Id
    @UuidGenerator
    private UUID id;

    @Column(nullable = false, length = 32)
    private String name;

    @Column(nullable = false)
    private int capacity;

    @Column(nullable = false)
    @Convert(converter = HashMapConverter.class)
    private Map<Integer, Integer> seats = new HashMap<>();
}
