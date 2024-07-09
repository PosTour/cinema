package ru.croc.team4.administration.service;



import ru.croc.team4.administration.domain.User;
import ru.croc.team4.administration.dto.UserDto;

import java.util.Optional;

public interface UserService {

    UserDto createUser(UserDto userDto);

    Optional<User> getUserByPhone(String phone);

    Iterable<UserDto> getAllUsers();
}
