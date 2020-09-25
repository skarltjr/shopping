package com.example.shopping.dto;

import com.example.shopping.domain.Address;
import lombok.Data;

@Data
public class MemberSearchCond {
    private String username;
    private Address address;
}
