package com.example.shopping.service;

import com.example.shopping.domain.Member;
import com.example.shopping.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //회원가입
    @Transactional
    public Long join(Member member) {
        duplicate(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void duplicate(Member member) {
        List<Member> result = memberRepository.findByUsername(member.getUsername());
        if(!result.isEmpty())  // 이 부분을 처음에 result!= null로 했을 때 오류가 있었다.
            //list로 리턴해줄때 없어도 null로 리턴을 하지않는다는거 기억하기
        {
            throw new IllegalStateException("중복된 이름의 회원 존재");
        }
    }

    //전체회원
    public List<Member> findAll()
    {
        return memberRepository.findAll();
    }

    //이름 동일한 회원 전체 조회
    public List<Member> findSameName(String name) {
         return memberRepository.findByUsername(name);
    }

    //단건 회원조회
    public Member findOne(Long id)
    {
        return memberRepository.findById(id).get();
        //편의상 get
    }

    //update
    public void updateMember(Long id,String name)
    {
        Member member = memberRepository.findById(id).get();
        member.setUsername(name);
    }

}
