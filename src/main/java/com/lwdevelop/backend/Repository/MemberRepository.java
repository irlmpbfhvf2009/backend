package com.lwdevelop.backend.repository;

/* package com.lwdevelop.backend.repository; */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.lwdevelop.backend.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member,Integer> {
    public Member findByEmail(String member);
    public Member findByPassword(String password);
    public Member findByUsername(String username);
    public void deleteByEmail(String email);
    public void deleteByUsername(String username);
}
