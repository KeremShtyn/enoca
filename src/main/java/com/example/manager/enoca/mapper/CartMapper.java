package com.example.manager.enoca.mapper;

import com.example.manager.enoca.domain.Cart;
import com.example.manager.enoca.entity.CartEntity;
import com.example.manager.util.base.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartMapper extends BaseMapper<CartEntity, Cart> {


}
