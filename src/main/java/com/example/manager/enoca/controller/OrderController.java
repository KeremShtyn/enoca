package com.example.manager.enoca.controller;


import com.example.manager.enoca.api.OrderApi;
import com.example.manager.enoca.domain.Order;
import com.example.manager.enoca.dto.OrderDTO;
import com.example.manager.enoca.mapper.OrderDtoMapper;
import com.example.manager.enoca.service.OrderService;
import com.example.manager.util.response.EnocaGenerator;
import com.example.manager.util.response.EnocaResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Slf4j
public class OrderController implements OrderApi {

    private OrderService orderService;

    private OrderDtoMapper orderDtoMapper;

    public OrderController(OrderService orderService, OrderDtoMapper orderDtoMapper) {
        this.orderService = orderService;
        this.orderDtoMapper = orderDtoMapper;
    }

    @Override
    public EnocaResponse<Object> placeOrder(String username) {
        Order order = orderService.placeOrder(username);
        return EnocaGenerator.generateSignResponse(orderDtoMapper.toDTO(order));
    }

    @Override
    public EnocaResponse<Object> getOrderByOrderCode(String orderCode) {
        Order order = orderService.findByOrderCode(orderCode);
        return EnocaGenerator.generateSignResponse(orderDtoMapper.toDTO(order));
    }

    @Override
    public EnocaResponse<Object> getOrdersByUser(String userId) {
        List<Order> orders = orderService.findOrdersByUser(userId);
        return EnocaGenerator.generateSignResponse(orderDtoMapper.toListDTO(orders));
    }
}
