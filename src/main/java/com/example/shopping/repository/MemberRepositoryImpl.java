package com.example.shopping.repository;

import com.example.shopping.domain.Address;
import com.example.shopping.domain.Member;
import com.example.shopping.domain.QAddress;
import com.example.shopping.dto.MemberDto;
import com.example.shopping.dto.MemberSearchCond;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.shopping.domain.QMember.member;
import static org.springframework.util.StringUtils.hasText;

public class MemberRepositoryImpl implements MemberRepositoryCustom{

    private EntityManager em;
    private JPAQueryFactory queryFactory;

    public MemberRepositoryImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<MemberDto> searchMember() {
        return queryFactory
                .select(Projections.constructor(MemberDto.class,
                        member.username,member.address))
                .from(member)
                .fetch();
    }

    @Override
    public Page<MemberDto> search(MemberSearchCond cond,Pageable pageable) {
        QueryResults<Member> members = queryFactory
                .select(member)
                .from(member)
                .where(nameEq(cond.getUsername()),
                        addressEq(cond.getAddress()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<Member> results = members.getResults();
        List<MemberDto> content = results.stream().map(r -> new MemberDto(r)).collect(Collectors.toList());
        long total = members.getTotal();
        return new PageImpl<>(content,pageable,total);
    }

    private BooleanExpression nameEq(String username) {
        return hasText(username)?member.username.eq(username) : null;
    }

    private BooleanExpression addressEq(Address address) {
        return address != null ? QAddress.address.eq(address) : null;
    }

    @Override
    public Page<MemberDto> pagingMembers(Pageable pageable) {
        QueryResults<MemberDto> results = queryFactory
                .select(Projections.constructor(MemberDto.class,
                        member.username, member.address))
                .from(member)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        List<MemberDto> contents = results.getResults();
        long total = results.getTotal();
        return new PageImpl<>(contents,pageable,total);
    }
    //혹은 booleanBuilder 사용 


    @Override
    public List<MemberDto> member2() {
        List<Member> members = queryFactory
                .select(member)
                .from(member)
                .fetch();
        return members.stream().map(m->new MemberDto(m)).collect(Collectors.toList());
    }
}
