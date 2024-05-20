package com.example.manager.enoca.domain;

import com.example.manager.authentication.domain.User;
import com.example.manager.util.base.BaseDomain;
import lombok.Data;

import java.util.Map;

@Data
public class Order extends BaseDomain {

    private User user;
    private Map<Product, Integer> products;
    private double totalPrice;
    private String orderCode;
}
