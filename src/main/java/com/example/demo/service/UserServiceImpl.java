package com.example.demo.service;

import com.example.demo.dao.UserDao;
import com.example.demo.dao.UserDaoImpl;
import com.example.demo.dto.UserDto;
import com.example.demo.dto.UserFilterDto;
import com.example.demo.mapper.UserDtoToUserEntityMapper;
import com.example.demo.mapper.UserEntityToUserDtoMapper;
import com.example.demo.model.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final UserDaoImpl userDaoImpl;
    private final UserDtoToUserEntityMapper userDtoToUserEntityMapper;
    private final UserEntityToUserDtoMapper userEntityToUserDtoMapper;

    @Override
    public UserDto addNewUser(UserDto userDto) {
        UserEntity userEntity = userDtoToUserEntityMapper.mapTo(userDto);
        UserEntity save = userDao.save(userEntity);
        return userEntityToUserDtoMapper.mapTo(save);
    }

    @Override
    public List<UserDto> filterUser(UserFilterDto userFilterDto) {
        List<UserEntity> all = userDaoImpl.filter(userFilterDto);
        List<UserDto> result = all.stream()
                .map(userEntityToUserDtoMapper::mapTo)
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public void delete(Integer id) {
        userDao.deleteById(id);
    }
}
