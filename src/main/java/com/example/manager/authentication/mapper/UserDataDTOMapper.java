package com.example.manager.authentication.mapper;

import com.example.manager.authentication.domain.User;
import com.example.manager.authentication.dto.UserDataDTO;
import com.example.manager.util.base.BaseDTOMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserDataDTOMapper extends BaseDTOMapper<User, UserDataDTO> {
}
