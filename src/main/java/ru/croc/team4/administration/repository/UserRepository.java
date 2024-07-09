package ru.croc.team4.administration.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.croc.team4.administration.domain.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByPhone(String phone);
}
