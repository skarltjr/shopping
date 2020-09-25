package com.example.shopping.dto.request;

import com.example.shopping.domain.Address;
import lombok.Data;

@Data
public class CreateMemberRequest {
    private String username;
    private Address address;
}
