package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    @NotEmpty(message = "first_name cannot be null")
    private String firstName;
    @NotEmpty(message = "last_name cannot be null")
    private String lastName;
    @NotEmpty(message = "age cannot be null")
    private Integer age;
}
