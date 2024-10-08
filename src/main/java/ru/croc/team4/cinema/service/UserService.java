package ru.croc.team4.cinema.service;



import ru.croc.team4.cinema.domain.User;
import ru.croc.team4.cinema.dto.UserDto;

import java.util.Optional;

public interface UserService {

    void createUser(UserDto userDto);

    Optional<User> getUserByPhone(String phone);

    Iterable<UserDto> getAllUsers();

    Optional<User> getUserByChatId(Long chatId);
}
