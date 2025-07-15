package io.backend.springBoot_DTO.Model;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring",uses =LocationDTO.class)
public class UserWithLocationMapping {

    @Autowired
    private LocationDTO locationDTO;


}
