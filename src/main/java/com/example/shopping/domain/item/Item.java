package com.example.shopping.domain.item;

import com.example.shopping.domain.exception.NotEnoughStockException;
import com.querydsl.core.annotations.Config;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;

@Entity
@Getter @Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="dtype")
public abstract class Item {
    @Id
    @GeneratedValue
    @Column(name="item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    public Item( ) {}

    public Item(String name, int price, int stockQuantity) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public void removeStock(int count) {
        int restStock= stockQuantity-count;
        if(restStock<=0) {
            throw new NotEnoughStockException("need more stock");
        }
        stockQuantity=restStock;
    }

    public void addStock(int count) {
        this.stockQuantity+=count;
    }
}
