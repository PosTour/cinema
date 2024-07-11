package ru.croc.team4.cinema.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.croc.team4.cinema.domain.User;
import ru.croc.team4.cinema.dto.UserDto;
import ru.croc.team4.cinema.mapper.UserMapper;
import ru.croc.team4.cinema.mapper.UserMapperImpl;
import ru.croc.team4.cinema.service.UserService;

import java.util.Optional;

@RestController
@RequestMapping("api/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
        this.userMapper = new UserMapperImpl();
    }

    @GetMapping("{phone}")
    public ResponseEntity<UserDto> getUserById(@PathVariable ("phone") String phone) {
        Optional<User> user = userService.getUserByPhone(phone);
        return user.map(value -> ResponseEntity.ok(userMapper.userToUserDto(value))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("/signup")
    public ResponseEntity<Void> signUp(@RequestBody UserDto userDto) {
        Optional<User> user = userService.getUserByPhone(userDto.phone());
        if (user.isEmpty()) {
            userService.createUser(userDto);
        }
        return ResponseEntity.ok().build();
    }
}
