package com.example.shopping.service;

import com.example.shopping.domain.*;
import com.example.shopping.domain.item.Item;
import com.example.shopping.repository.ItemRepository;
import com.example.shopping.repository.MemberRepository;
import com.example.shopping.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    //주문
    public Order createOrder(Long memberId,Long itemId,int count)
    {
        Member member = memberRepository.findById(memberId).get();
        Item item = itemRepository.findById(itemId).get();

        Delivery delivery =new Delivery();
        delivery.setAddress(member.getAddress());

        OrderItem orderItem=OrderItem.createOrderItem(item.getPrice(),count,item);

        Order order=Order.createOrder(member,delivery,orderItem);
        orderRepository.save(order);
        return order;
    }

    //주문 취소
    @Transactional
    public void cancel(Long id)
    {
        Order order = orderRepository.findById(id).get();
        order.setStatus(OrderStatus.CANCEL);
    }

    public List<Order> findOrders()
    {
        return orderRepository.findAll();
    }


}
