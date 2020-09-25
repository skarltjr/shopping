package com.example.shopping.dto;

import com.example.shopping.domain.Address;
import com.example.shopping.domain.Member;
import lombok.Data;

@Data
public class MemberDto {

    private String username;
    private Address address;

    public MemberDto() {
    }

    public MemberDto(String username, Address address) {
        this.username = username;
        this.address = address;
    }

    public MemberDto(Member member) {
        this.username= member.getUsername();;
        this.address= member.getAddress();
    }
}
