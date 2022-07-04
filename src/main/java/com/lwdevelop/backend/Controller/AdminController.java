package com.lwdevelop.backend.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lwdevelop.backend.entity.Member;
import com.lwdevelop.backend.service.MemberService;
import com.lwdevelop.backend.vo.EmailVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(tags = "管理員接口")
@RestController
/* @CrossOrigin(origins = "*") */
@RequestMapping("/admin")
public class AdminController {
    
    @Autowired
    MemberService memberService;

    @ApiOperation("查詢用戶")
    @PostMapping(path = "/findByEmail")
    public ResponseEntity<Member> findByEmail(
                                    HttpServletRequest request, 
                                    @RequestBody EmailVO email) throws Exception{
        log.info("AdminController ==> findByEmail ........... 查詢用戶：[" + memberService.findByEmail(email.getEmail())+" ]");
        return ResponseEntity.status(HttpStatus.OK).body(memberService.findByEmail(email.getEmail()));
    }

}
