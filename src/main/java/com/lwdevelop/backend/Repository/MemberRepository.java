package com.lwdevelop.backend.Repository;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lwdevelop.backend.Entity.Member;

@Configuration
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    
}
