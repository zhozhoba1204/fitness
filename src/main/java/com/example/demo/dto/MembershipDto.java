package com.example.demo.dto;

import com.example.demo.model.MembershipEntity;
import com.example.demo.model.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MembershipDto {
    @NotEmpty(message = "endDate cannot be null")
    private String endDate;
    @NotEmpty(message = "section cannot be null")
    private String section;
    @NotEmpty(message = "user_id cannot be null")
    private Integer userEntity;

    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static MembershipEntity getMembershipFromDto(UserEntity userEntity, MembershipDto membershipDto) {
        return new MembershipEntity(LocalDate.parse(membershipDto.getEndDate(), formatter),membershipDto.getSection(),userEntity);
    }
}
