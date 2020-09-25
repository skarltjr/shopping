package com.example.shopping.controller;

import com.example.shopping.domain.Member;
import com.example.shopping.dto.MemberDto;
import com.example.shopping.dto.MemberSearchCond;
import com.example.shopping.dto.request.CreateMemberRequest;
import com.example.shopping.dto.request.CreateMemberResponse;
import com.example.shopping.repository.MemberRepository;
import com.example.shopping.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberApiController {
    private final MemberRepository memberRepository;
    private final MemberService memberService;

    @GetMapping("/members")
    public List<MemberDto> members()
    {
        return memberRepository.searchMember();
    }

    //멤버 전체 페이징으로
    @GetMapping("/pagingMembers")
    public Page<MemberDto> pagingMembers(Pageable pageable) {
        return memberRepository.pagingMembers(pageable);
    }

    //멤버 cond로 동적
    @GetMapping("/condMembers")
    public Page<MemberDto> condMembers(MemberSearchCond cond, Pageable pageable)
    {
        return memberRepository.search(cond,pageable);
    }

    //멤버 가입    memberRequest response dto에 만들어서
    @PostMapping("/createMember")
    public CreateMemberResponse createMember(@RequestBody @Valid CreateMemberRequest request)
    {
        Member member = new Member(request.getUsername(), request.getAddress());
        Long join = memberService.join(member);
        return new CreateMemberResponse(join,member.getUsername());
    }

    @GetMapping("/member2")
    public List<MemberDto> member2()
    {
        return memberRepository.member2();
    }
    //update는 pumapping으로

}
