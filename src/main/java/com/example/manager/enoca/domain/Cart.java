package com.example.manager.enoca.domain;

import lombok.Data;
import java.util.Map;

@Data
public class Cart {

    private String userId;
    private Map<Product, Integer> products;
    private double totalPrice;
    private int quantity;
}
