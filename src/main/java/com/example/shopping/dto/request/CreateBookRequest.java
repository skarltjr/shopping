package com.example.shopping.dto.request;

import lombok.Data;

//편의를 위해 북 종류의 아이템만 사용

@Data
public class CreateBookRequest {
    private String itemName;
    private int price;
    private int stockQuantity;
    private String author;

    public CreateBookRequest(String itemName, int price, int stockQuantity,String author) {
        this.itemName = itemName;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.author=author;
    }

    public CreateBookRequest() {
    }
}
