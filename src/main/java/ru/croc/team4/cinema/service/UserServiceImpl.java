package ru.croc.team4.cinema.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.croc.team4.cinema.domain.User;
import ru.croc.team4.cinema.dto.UserDto;
import ru.croc.team4.cinema.mapper.UserMapper;
import ru.croc.team4.cinema.mapper.UserMapperImpl;
import ru.croc.team4.cinema.repository.UserRepository;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.userMapper = new UserMapperImpl();
    }

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
