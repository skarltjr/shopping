package com.example.shopping.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "orders")
@Setter
public class Order {
    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="delivery_id")
    private Delivery delivery;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems=new ArrayList<>();

    public Order() {}

    // 양방향 연관관계 편의 매서드
    public void setMember(Member member) {
        this.member=member;
        member.getOrders().add(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery=delivery;
        delivery.setOrder(this);
    }

    public void addOrderItem(OrderItem orderItem) {
        getOrderItems().add(orderItem);
        orderItem.setOrder(this);
    }

    //생성매서드
    public static Order createOrder(Member member,Delivery delivery,OrderItem... orderItems)
    {
        Order order = new Order();
        order.setDelivery(delivery);
        order.setMember(member);

        for (OrderItem orderItem : orderItems) {
            order.addOrderItem(orderItem);
        }
        order.setStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());
        return order;
    }

    //비즈니스로직
    //cancel
    public void cancel()
    {
        if(delivery.getStatus() == DeliveryStatus.COMP)
        {
            throw new IllegalStateException("이미 배송중");
        }
        this.setStatus(OrderStatus.CANCEL);
        for (OrderItem orderItem : orderItems) {
            orderItem.cancel();
        }
    }

    public int getTotalPrice()
    {
        int totalPrice=0;
        for(OrderItem orderItem : orderItems)
        {
            totalPrice+=orderItem.getTotalPrice();
        }
        return totalPrice;
    }


}
