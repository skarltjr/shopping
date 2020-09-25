package com.example.shopping.repository;

import com.example.shopping.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface MemberRepository extends JpaRepository<Member,Long> ,MemberRepositoryCustom{
    List<Member> findByUsername(String username);
}
