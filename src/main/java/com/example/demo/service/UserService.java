package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.dto.UserFilterDto;

import java.util.List;

public interface UserService {
    UserDto addNewUser(UserDto user);

    List<UserDto> filterUser(UserFilterDto userFilterDto);

    void delete(Integer id);
}
