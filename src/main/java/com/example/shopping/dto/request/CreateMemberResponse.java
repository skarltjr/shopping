package com.example.shopping.dto.request;

import lombok.Data;

@Data
public class CreateMemberResponse {
    private Long id;
    private String username;

    public CreateMemberResponse(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public CreateMemberResponse() {
    }
}
