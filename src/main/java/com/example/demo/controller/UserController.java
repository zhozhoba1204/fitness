package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/")
public class UserController {

    private UserServiceImpl userServiceImpl;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @PostMapping("/addUser")
    public UserDto addNewUser(@RequestBody UserDto dto){
        return userServiceImpl.addNewUser(dto);
    }

    @GetMapping("/all")
    public List<UserDto> all(){
        return userServiceImpl.allUsers();
    }
}
