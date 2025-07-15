package io.backend.springBoot_DTO.Model;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;


@Mapper(componentModel = "spring")
public interface UserLocationMapper {
    UserLocationMapper INSTANCE= Mappers.getMapper(UserLocationMapper.class);
    @Mapping(source = "id",target = "userId")
    @Mapping(source = "location.place",target ="place")
    @Mapping(source = "location.latitude",target = "latitude")
    @Mapping(source = "location.longitude",target = "longitude")
    @Mapping(source = "email",target = "email")
    UserLocationDTO userToUserLocationDTO(User user);

    List<UserLocationDTO>usersToUsersLocationDTO(List<User>users);


}
