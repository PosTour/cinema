package ru.croc.team4.administration.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.croc.team4.administration.domain.User;
import ru.croc.team4.administration.dto.UserDto;
import ru.croc.team4.administration.mapper.UserMapper;
import ru.croc.team4.administration.repository.UserRepository;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = userRepository.save(new User(userDto.phone()));
        return userMapper.userToUserDto(user);
    }

    @Override
    public Optional<User> getUserByPhone(String phone) {
        return userRepository.findByPhone(phone);
    }

    @Override
    public Iterable<UserDto> getAllUsers() {
        return userMapper.userToUserDtoIterable(userRepository.findAll());
    }
}
