package com.example.shopping.controller;

import com.example.shopping.domain.Order;
import com.example.shopping.dto.OrderDto;

import com.example.shopping.dto.OrderSearchCond;
import com.example.shopping.repository.OrderRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class OrderApiController {
    private final OrderRepository orderRepository;

    @GetMapping("/orders")
    public List<OrderDto> orders() {//OrderSearchCond cond
        List<Order> search = orderRepository.orders();
        return search.stream().map(s->new OrderDto(s))
                .collect(Collectors.toList());
    }

    @GetMapping("/dynamicOrder")
    public Page<OrderDto> orders2(OrderSearchCond cond, Pageable pageable)
    {
        return orderRepository.searchOrderPaging(cond,pageable);
    }
}
