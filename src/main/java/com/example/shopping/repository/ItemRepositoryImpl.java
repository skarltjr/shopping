package com.example.shopping.repository;

import com.example.shopping.dto.ItemDto;
import com.example.shopping.dto.ItemSearchCond;
import com.querydsl.core.QueryResults;
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

import static com.example.shopping.domain.item.QBook.book;
import static com.example.shopping.domain.item.QItem.item;
import static org.springframework.util.StringUtils.hasText;

public class ItemRepositoryImpl implements ItemRepositoryCustom{

    private EntityManager em;
    private JPAQueryFactory queryFactory;

    public ItemRepositoryImpl(EntityManager em) {
        this.em = em;
        this.queryFactory=new JPAQueryFactory(em);
    }
    /**디폴트 생성자 만들었더니 nullpointerexception    기억하기 */



    @Override
    public List<ItemDto> searhAll() {
        return queryFactory
                .select(Projections.constructor(ItemDto.class,
                        item.name,item.stockQuantity,item.price))
                .from(item)
                .fetch();
    }

    @Override
    public Page<ItemDto> searchItem(ItemSearchCond cond, Pageable pageable) {
        QueryResults<ItemDto> result = queryFactory
                .select(Projections.constructor(ItemDto.class,
                        item.name, item.stockQuantity, item.price))
                .from(item)
                .where(nameEq(cond.getName()),
                        priceGoe(cond.getPrice()),
                        stockQuantityGoe(cond.getStockQuantity()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        List<ItemDto> content = result.getResults();
        long total = result.getTotal();

        return new PageImpl<>(content,pageable,total);
    }

    private BooleanExpression nameEq(String name) {
        return StringUtils.hasText(name) ? item.name.eq(name) : null;
    }

    private BooleanExpression priceGoe(Integer price) {
        return price!=null ? item.price.goe(price) : null;
    }

    private BooleanExpression stockQuantityGoe(Integer stockQuantity) {
        return stockQuantity != null ? item.stockQuantity.goe(stockQuantity) : null;
    }
}
