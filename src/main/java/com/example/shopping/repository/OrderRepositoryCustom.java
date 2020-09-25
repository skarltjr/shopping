package com.example.shopping.repository;

import com.example.shopping.domain.Order;
import com.example.shopping.dto.OrderDto;
import com.example.shopping.dto.OrderSearchCond;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderRepositoryCustom {
    List<Order> orders();
    Page<OrderDto> searchOrderPaging(OrderSearchCond cond, Pageable pageable);
}
