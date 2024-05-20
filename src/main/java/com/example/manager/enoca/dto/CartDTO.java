package com.example.manager.enoca.dto;

import lombok.Data;

import java.util.Map;


@Data
public class CartDTO {

    private String userId;
    private Map<ProductDTO, Integer> products;
    private double totalPrice;
    private int quantity;
}
