package com.lwdevelop.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lwdevelop.backend.Entity.Member;

public interface MemberRepository extends JpaRepository<Member,Integer> {
    
}
