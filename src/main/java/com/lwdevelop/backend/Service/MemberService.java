package com.lwdevelop.backend.Service;

import java.util.List;
import java.util.Optional;
import com.lwdevelop.backend.Entity.Member;

public interface MemberService {
    public Member saveMember(Member member);
    public Member save(Member member);
    public List<Member> findAll();
    public Optional<Member> findById(Long id);
    public void deleteById(Long id);
}
