package com.example.manager.authentication.mapper;

import com.example.manager.authentication.domain.User;
import com.example.manager.authentication.entity.UserEntity;
import com.example.manager.util.base.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<UserEntity, User> {
}
