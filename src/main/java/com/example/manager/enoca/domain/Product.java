package com.example.manager.enoca.domain;

import com.example.manager.util.base.BaseDomain;
import lombok.Data;

@Data
public class Product extends BaseDomain {

    private String name;
    private double price;
    private int stock;
}
