package ru.alexey.site.controller;
/* 
06.03.2022: Alexey created this file inside the package: ru.alexey.site 
*/

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.alexey.site.entity.User;
import ru.alexey.site.service.UserService;

@RestController
@RequestMapping("api/v1/user")
public class MainController {
    private final UserService userService;

    public MainController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<User> showUser(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(userService.findById(id));
    }

    @GetMapping(path = "admin")
    public ResponseEntity<String> showAdmin(){
        return ResponseEntity.ok("ADMIN");
    }

    @GetMapping(path = "feed")
    public ResponseEntity<String> showUser(){
        return ResponseEntity.ok("USER");
    }

}
