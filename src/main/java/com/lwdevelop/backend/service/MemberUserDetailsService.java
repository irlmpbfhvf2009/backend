package com.lwdevelop.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.lwdevelop.backend.entity.Member;
import com.lwdevelop.backend.repository.MemberRepository;

@Service
public class MemberUserDetailsService implements UserDetailsService {

    @Autowired
    MemberRepository memberRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        Member member = memberRepository.findByEmail(username);
        
        UserDetails userDetails = User.builder()
                .username(member.getEmail())
                        .password("{noop}"+member.getPassword()) 
                        .roles(member.getRoles().get(0))
                        .authorities(new SimpleGrantedAuthority(member.getRoles().get(0)))
                        .build();
        return userDetails;

    }
    
}
