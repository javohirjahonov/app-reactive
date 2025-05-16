package com.reactive.appreactive.mapper;

import com.reactive.appreactive.dto.UserDTO;
import com.reactive.appreactive.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDTO(User user);
}
