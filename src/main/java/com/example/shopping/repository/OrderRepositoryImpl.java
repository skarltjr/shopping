package com.example.shopping.repository;

import com.example.shopping.domain.*;
import com.example.shopping.domain.item.QItem;
import com.example.shopping.dto.OrderDto;
import com.example.shopping.dto.OrderSearchCond;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;

import static com.example.shopping.domain.QDelivery.delivery;
import static com.example.shopping.domain.QMember.member;
import static com.example.shopping.domain.QOrder.order;
import static com.example.shopping.domain.QOrderItem.orderItem;
import static com.example.shopping.domain.item.QItem.item;
import static org.springframework.util.StringUtils.hasText;


public class OrderRepositoryImpl implements OrderRepositoryCustom{

    private EntityManager em;
    private JPAQueryFactory queryFactory;

    public OrderRepositoryImpl(EntityManager em) {
        this.em = em;
        this.queryFactory=new JPAQueryFactory(em);
    }

    @Override
    public List<Order> orders() {
            return queryFactory
                .select(order).distinct()
                .from(order)
                .join(order.member, member).fetchJoin()
                .join(order.delivery, delivery).fetchJoin()
                .join(order.orderItems, orderItem).fetchJoin()
                .join(orderItem.item, item).fetchJoin()
                .fetch();
    }
    /**
     * 실수한 부분 = 레퍼지토리에서 바로 dto 로 변환해서 컨트롤러에서 보내려고했다.
     *
     * ★fetch join 자체가 엔티티의 객체 그래프를 조회할 때 사용하는 기능.
     * 따라서 fetch join을 사용하려면 엔티티로 조회
     * DTO로 조회하는 것이 불가.
     * */

    @Override
    public Page<OrderDto> searchOrderPaging(OrderSearchCond cond, Pageable pageable) {
        QueryResults<OrderDto> result = queryFactory
                .select(Projections.constructor(OrderDto.class,
                        order)).distinct()
                .from(order)
                .join(order.member, member)
                .join(order.delivery, delivery)
                .join(order.orderItems, orderItem)
                .join(orderItem.item, item)
                .where(nameEq(cond.getMemberName()), statusEq(cond.getStatus()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        List<OrderDto> contents = result.getResults();
        long total = result.getTotal();
        return new PageImpl<>(contents,pageable,total);
    }

    private BooleanExpression nameEq(String memberName) {
        return hasText(memberName) ? order.member.username.eq(memberName) : null;
    }

    private BooleanExpression statusEq(OrderStatus status) {
        return status != null ? order.status.eq(status) : null;
        //status 이넘에 getter추가
    }
}
