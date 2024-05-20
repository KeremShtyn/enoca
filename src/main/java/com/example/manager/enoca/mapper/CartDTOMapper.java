package com.example.manager.enoca.mapper;

import com.example.manager.enoca.domain.Cart;
import com.example.manager.enoca.dto.CartDTO;
import com.example.manager.util.base.BaseDTOMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartDTOMapper extends BaseDTOMapper<Cart, CartDTO> {
}
