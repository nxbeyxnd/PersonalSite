package ru.alexey.site.controller;
/* 
06.03.2022: Alexey created this file inside the package: ru.alexey.site 
*/

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.alexey.site.dto.UserRegisterRequestDto;
import ru.alexey.site.dto.UserResponseDto;
import ru.alexey.site.entity.User;
import ru.alexey.site.service.UserService;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "")
    public ResponseEntity<List<UserResponseDto>> findAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<UserResponseDto> findUserById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<UserResponseDto> updateUserById(@PathVariable(name = "id") Long id,
                                                          @RequestBody UserRegisterRequestDto userRegisterRequestDto) {
        return ResponseEntity.ok(userService.update(id, userRegisterRequestDto));
    }

    @PostMapping(path = "")
    public ResponseEntity<UserResponseDto> addNewUser(@RequestBody UserRegisterRequestDto userRegisterRequestDto) {
        return ResponseEntity.ok(userService.save(userRegisterRequestDto));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<User> removeUserById(@PathVariable(name = "id") long id) {
        userService.removeUserById(id);
        return ResponseEntity.noContent().build();
    }
}