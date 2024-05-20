package com.example.manager.enoca.dto;

import com.example.manager.authentication.dto.UserDTO;
import com.example.manager.util.base.BaseDTO;
import lombok.Data;

import java.util.Map;


@Data
public class OrderDTO extends BaseDTO {
    private UserDTO user;
    private Map<ProductDTO, Integer> products;
    private double totalPrice;
    private String orderCode;
}
