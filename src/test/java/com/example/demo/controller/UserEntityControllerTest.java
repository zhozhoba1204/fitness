package com.example.demo.controller;

import com.example.demo.DemoApplication;
import com.example.demo.dto.UserDto;
import com.example.demo.dto.UserFilterDto;
import com.example.demo.mapper.UserDtoToUserEntityMapper;
import com.example.demo.mapper.UserEntityToUserDtoMapper;
import com.example.demo.model.UserEntity;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = DemoApplication.class)
@Sql(scripts = {"/db/migration/schema-test.sql", "/db/migration/data-test.sql"})
@AutoConfigureMockMvc
class UserEntityControllerTest {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserDtoToUserEntityMapper userDtoToUserEntityMapper;
    @Autowired
    private UserEntityToUserDtoMapper userEntityToUserDtoMapper;

    @Test
    public void shouldReturnSuccessWhenParameterIsUser() throws Exception {
        UserDto request = new UserDto("Ivan", "Ivanov", 30, 11112);
        UserDto response = userService.addNewUser(request);
        UserEntity userEntity = userDtoToUserEntityMapper.mapTo(request);
        mockMvc.perform(post("/addUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
        assertThat(userEntity.getClass(), equalTo(userDtoToUserEntityMapper.mapTo(response).getClass()));
    }


    @Test
    public void shouldReturnSuccessWhenTheNumberOfRowsInDatabaseHasIncreased() throws Exception {
        UserDto request = new UserDto("Ivan", "Ivanov", 30, 11113);
        UserFilterDto userFilterDto = new UserFilterDto();
        List<UserDto> firstList = userService.filterUser(userFilterDto);
        userService.addNewUser(request);
        List<UserDto> secondList = userService.filterUser(userFilterDto);
        assertThat(secondList.size(), is(firstList.size() + 1));
    }

    @Test
    public void shouldReturnSuccessWhenResultListIsNotNull() throws Exception {
        UserFilterDto userFilterDto = new UserFilterDto();
        List<UserDto> list = userService.filterUser(userFilterDto);
        mockMvc.perform(post("/user/filter")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userFilterDto)))
                .andExpect(status().isOk());
        assertThat(list, notNullValue());
    }

    @Test
    public void shouldReturnSuccessWhenLastRowEqualsRequest() throws Exception {
        UserFilterDto userFilterDto = new UserFilterDto();
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName("Slava");
        userEntity.setLastName("Blinov");
        userEntity.setAge(31);
        userEntity.setPassport(12345);
        UserDto request = userEntityToUserDtoMapper.mapTo(userEntity);
        userService.addNewUser(request);
        List<UserDto> list = userService.filterUser(userFilterDto);
        mockMvc.perform(post("/user/filter")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userFilterDto)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(list)));
        assertThat(list.get(list.size() - 1), equalTo(request));
    }
}