package com.lwdevelop.backend.Controller;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lwdevelop.backend.Entity.Member;
import com.lwdevelop.backend.Service.MemberService;

@RestController
@RequestMapping("/api")
public class MemberController {

    @Autowired
    private MemberService memberService;
    
    @GetMapping("/members")
    public Collection<Member> members() {
        return memberService.findAll();
    }
    @GetMapping("/test")
    public Collection<Member> members() {
        return ResponseEntity.ok().body("test");
    }
    
    @GetMapping("/member/{id}")
    public ResponseEntity<?> getMember(@PathVariable Long id) {
        Optional<Member> member = memberService.findById(id);
        return member.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/member")
    public ResponseEntity<Member> createMember(@RequestBody Member member) throws Exception {
        Member result = memberService.saveMember(member);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("/member/{id}")
    public ResponseEntity<Member> updateMember(@RequestBody Member member) {
        Member result = memberService.save(member);
        return ResponseEntity.ok().body(result);
    }
    
    @DeleteMapping("/member/{id}")
    public ResponseEntity<?> deleteMember(@PathVariable Long id){
        memberService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
