package ru.croc.team4.cinema.mapper;

import org.mapstruct.Mapper;
import ru.croc.team4.cinema.domain.User;
import ru.croc.team4.cinema.dto.UserDto;


@Mapper
public interface UserMapper {
    UserDto userToUserDto(User user);
    User userDtoToUser(UserDto userDto);
    Iterable<UserDto> userToUserDtoIterable(Iterable<User> users);
}
