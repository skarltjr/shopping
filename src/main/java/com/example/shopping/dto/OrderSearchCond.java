package com.example.shopping.dto;

import com.example.shopping.domain.OrderStatus;
import lombok.Data;

@Data
public class OrderSearchCond {
    private String memberName;
    private OrderStatus status;
}
