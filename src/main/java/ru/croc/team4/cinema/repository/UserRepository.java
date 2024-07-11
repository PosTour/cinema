package ru.croc.team4.cinema.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.croc.team4.cinema.domain.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByPhone(String phone);
    Optional<User> findByChatId(Long chatId);
}
