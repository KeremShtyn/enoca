package com.example.manager.enoca.mapper;

import com.example.manager.enoca.domain.Order;
import com.example.manager.enoca.dto.OrderDTO;
import com.example.manager.util.base.BaseDTOMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderDtoMapper extends BaseDTOMapper<Order, OrderDTO> {
}
