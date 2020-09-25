package com.example.shopping.repository;

import com.example.shopping.domain.Member;
import com.example.shopping.dto.MemberDto;
import com.example.shopping.dto.MemberSearchCond;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MemberRepositoryCustom {
    //그냥 전체조회
    List<MemberDto> searchMember();
    //동적쿼리로 이름eq,주소eq인 사람들 찾기
    Page<MemberDto> search(MemberSearchCond cond,Pageable pageable);
    Page<MemberDto> pagingMembers(Pageable pageable);
    List<MemberDto> member2();
}
