package com.example.shopping.dto.request;

import lombok.Data;

@Data
public class CreateBookResponse {
    private Long itemId;
    private String itemName;

    public CreateBookResponse() {
    }

    public CreateBookResponse(Long itemId, String itemName) {
        this.itemId = itemId;
        this.itemName = itemName;
    }
}
