package com.softserve.edu.sporthubujp.mapper;

import com.softserve.edu.sporthubujp.dto.UserDTO;
import com.softserve.edu.sporthubujp.entity.User;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface UserMapper {
    User dtoToEntity(UserDTO userDTO);

    UserDTO entityToDto(User user);
}
