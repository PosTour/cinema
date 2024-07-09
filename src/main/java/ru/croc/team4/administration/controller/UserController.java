package ru.croc.team4.administration.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.croc.team4.administration.domain.User;
import ru.croc.team4.administration.dto.UserDto;
import ru.croc.team4.administration.mapper.UserMapper;
import ru.croc.team4.administration.service.UserService;

import java.util.Optional;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor()
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("{phone}")
    public ResponseEntity<UserDto> getUserById(@PathVariable String phone) {
        Optional<User> user = userService.getUserByPhone(phone);
        return user.map(value -> ResponseEntity.ok(userMapper.userToUserDto(value))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.createUser(userDto));
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
}
