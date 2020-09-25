package com.example.shopping.dto;

import com.example.shopping.domain.OrderItem;
import lombok.Data;

@Data
public class OrderItemDto {
    private String itemName;
    private int count;
    private int orderPrice;

    public OrderItemDto(OrderItem orderItem) {
        this.count=orderItem.getCount();
        this.orderPrice=orderItem.getOrderPrice();
        this.itemName=orderItem.getItem().getName();
    }
}
