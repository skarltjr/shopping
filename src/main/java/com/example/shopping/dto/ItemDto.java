package com.example.shopping.dto;

import com.example.shopping.domain.item.Item;
import lombok.Data;

@Data
public class ItemDto {
    private String name;
    private int stockQuantity;
    private int price;

    public ItemDto() {
    }
    public ItemDto(String name, int stockQuantity, int price) {
        this.name = name;
        this.stockQuantity = stockQuantity;
        this.price = price;
    }

    public ItemDto(Item item) {
        this.name= item.getName();
        this.stockQuantity= item.getStockQuantity();
        this.price= item.getPrice();
    }
}
