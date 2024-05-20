package com.example.manager.enoca.dto;

import com.example.manager.util.base.BaseDTO;
import lombok.Data;

@Data
public class ProductDTO extends BaseDTO {
    private String name;
    private double price;
    private int stock;
}
