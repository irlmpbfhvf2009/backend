package com.lwdevelop.backend.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lwdevelop.backend.Entity.Member;
import com.lwdevelop.backend.Repository.MemberRepository;

@RestController
@RequestMapping("/api")
public class MemberController {

    @Autowired
    private MemberRepository memberRepository;
    
    @GetMapping("/add")
    public String addMember(@RequestParam(value="email") String email,
                            @RequestParam(value="password") String password) {
        Member member = new Member();
        member.setEmail(email);
        member.setPassword(password);
        memberRepository.save(member);
        return "saved";
    }
    @RequestMapping("/all")
    public @ResponseBody Iterable<Member> getAllMember() {
        return memberRepository.findAll();
    }

}
