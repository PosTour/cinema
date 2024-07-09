package ru.croc.team4.administration.mapper;

import org.mapstruct.Mapper;
import ru.croc.team4.administration.domain.User;
import ru.croc.team4.administration.dto.UserDto;


@Mapper
public interface UserMapper {
    UserDto userToUserDto(User user);

    Iterable<UserDto> userToUserDtoIterable(Iterable<User> users);
}
