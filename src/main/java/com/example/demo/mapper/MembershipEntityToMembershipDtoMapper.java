package com.example.demo.mapper;

import com.example.demo.dto.MembershipDto;
import com.example.demo.model.MembershipEntity;
import com.example.demo.model.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public abstract class MembershipEntityToMembershipDtoMapper {
    @Mapping(target = "endDate", source = "endDate",dateFormat = "dd-MM-yyyy")
    @Mapping(target = "section", source = "section")
    @Mapping(target = "userEntity", source = "userEntity", qualifiedByName = "mapUserEntities")
    public abstract MembershipDto mapTo(MembershipEntity membershipEntity);

    @Named("mapUserEntities")
    Integer mapUserEntities(UserEntity userEntity) {
    return userEntity.getId();
    }
}
