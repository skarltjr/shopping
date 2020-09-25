package com.example.shopping.domain;

import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter //편의를 위해 여기선 setter사용
public class Member {

    @Id
    @GeneratedValue
    @Column(name="member_id")
    private Long id;

    private String username;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")
    private List<Order> orders=new ArrayList<>();

    public Member() { }

    public Member(String username, Address address) {
        this.username = username;
        this.address = address;
    }
}
