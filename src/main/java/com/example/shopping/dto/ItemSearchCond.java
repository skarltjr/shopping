package com.example.shopping.dto;

import lombok.Data;

@Data
public class ItemSearchCond {
    private String name;
    private Integer stockQuantity;
    private Integer price;
}
