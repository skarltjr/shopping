package com.example.shopping.dto;

import com.example.shopping.domain.Address;
import com.example.shopping.domain.Order;
import com.example.shopping.domain.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class OrderDto {
    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus status;
    private Address address;
    private List<OrderItemDto> orderItems;

    public OrderDto(Order order)
    {
        this.orderId=order.getId();
        this.name=order.getMember().getUsername();
        this.orderDate=order.getOrderDate();
        this.status=order.getStatus();
        this.address=order.getDelivery().getAddress();
        this.orderItems = order.getOrderItems().stream().map(o -> new OrderItemDto(o)).collect(Collectors.toList());
    }
}
