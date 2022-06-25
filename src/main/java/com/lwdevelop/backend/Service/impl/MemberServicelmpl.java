package com.lwdevelop.backend.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lwdevelop.backend.Entity.Member;
import com.lwdevelop.backend.Repository.MemberRepository;
import com.lwdevelop.backend.Service.MemberService;

@Service
public class MemberServicelmpl implements MemberService {
    
    @Autowired
    private MemberRepository memberRepository;

    @Override
    public Member saveMember(Member member) {
        memberRepository.save(member);
        return member;
    }

}
