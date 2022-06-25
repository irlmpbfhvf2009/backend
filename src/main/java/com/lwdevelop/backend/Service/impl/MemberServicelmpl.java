package com.lwdevelop.backend.Service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lwdevelop.backend.Entity.Member;
import com.lwdevelop.backend.Repository.MemberRepository;
import com.lwdevelop.backend.Service.MemberService;


@Service
public class MemberServicelmpl implements MemberService {
    
    @Autowired(required=false)
    private MemberRepository memberRepository;

    @Override
    public Member saveMember(Member member) {
        memberRepository.save(member);
        return member;
    }

    @Override
    public Member save(Member member) {
        return memberRepository.save(member);
        
    }

    @Override
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    @Override
    public Optional<Member> findById(Long id) {
        return memberRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        memberRepository.deleteById(id);;
    }

}
