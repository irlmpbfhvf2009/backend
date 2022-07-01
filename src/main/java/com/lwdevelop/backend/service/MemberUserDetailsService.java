package com.lwdevelop.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
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
        if(member==null){
            throw new UsernameNotFoundException("用戶不存在");
        }
        if(!member.isEnabled()){
            throw new LockedException("帳號被禁用");
        }

        UserDetails userDetails = User.builder()
        .username(member.getEmail())
                .password("{noop}"+member.getPassword()) // 密碼前面加上"{noop}"使用NoOpPasswordEncoder，也就是不對密碼進行任何格式的編碼。
                .roles(member.getRoles().get(0))
                .build();
        return userDetails;
    }
    
}
