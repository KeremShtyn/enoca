package com.example.manager.enoca.mapper;

import com.example.manager.enoca.domain.Order;
import com.example.manager.enoca.entity.OrderEntity;
import com.example.manager.util.base.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper extends BaseMapper<OrderEntity, Order> {
}
