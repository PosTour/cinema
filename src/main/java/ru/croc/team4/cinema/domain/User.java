package ru.croc.team4.cinema.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@Table(name = "user", schema = "cinema")
public class User {
    /*
    * Идентификатор
    * */
    @Id
    @UuidGenerator
    private UUID id;

    /*
     * номер телефона (79991128537)
     * */
    @Column(unique=true, nullable = false, length = 11)
    private String phone;

    @Column(nullable = false)
    private String chatId;

    public User(String phone) {
        this.phone = phone;
    }

    public User() {

    }
}
