package com.example.shopping.repository;

import com.example.shopping.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long>,OrderRepositoryCustom{
}
