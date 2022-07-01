package com.lwdevelop.backend.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.lwdevelop.backend.entity.Member;
import com.lwdevelop.backend.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(tags = "管理員接口")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    MemberService memberService;

    @ApiOperation("查詢用戶")
    @GetMapping(path = "/findByEmail")
    public ResponseEntity<Member> findByEmail(
                                    UserDetails u,
                                    HttpServletRequest request, 
                                    @RequestParam String email) throws Exception{
        log.info("AdminController ==> register ........... 查詢用戶：[" + memberService.findByEmail(email)+" ]");
        return ResponseEntity.status(HttpStatus.OK).body(memberService.findByEmail(email));
    }

}
