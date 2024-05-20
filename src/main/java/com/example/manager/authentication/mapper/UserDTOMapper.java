package com.example.manager.authentication.mapper;


import com.example.manager.util.base.BaseDTOMapper;
import com.example.manager.authentication.domain.User;
import com.example.manager.authentication.dto.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserDTOMapper extends BaseDTOMapper<User, UserDTO> {
}
