package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.service.UserService;
import com.example.demo.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl) {
        this.userService = userServiceImpl;
    }

    @PostMapping("/addUser")
    public UserDto addNewUser(@RequestBody UserDto dto){
        return userService.addNewUser(dto);
    }

    @GetMapping("/all")
    public List<UserDto> all(){
        return userService.allUsers();
    }
}
