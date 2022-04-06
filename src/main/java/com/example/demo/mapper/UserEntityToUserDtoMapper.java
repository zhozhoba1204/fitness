package com.example.demo.mapper;

import com.example.demo.dto.UserDto;
import com.example.demo.model.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class UserEntityToUserDtoMapper {

    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "age", source = "age")
    public abstract UserDto mapTo(UserEntity userEntity);
}
