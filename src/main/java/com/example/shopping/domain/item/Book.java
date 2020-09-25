package com.example.shopping.domain.item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@DiscriminatorValue("B")
public class Book extends Item{
    private String author;
    private String isbn;

    public Book() {
    }

    public Book(String name, int price, int stockQuantity,String author) {
        super(name, price, stockQuantity);
        this.author=author;
    }
}
