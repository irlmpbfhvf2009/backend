package com.lwdevelop.backend.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lwdevelop.backend.service.MemberService;
import com.lwdevelop.backend.vo.MemberLoginVO;
import com.lwdevelop.backend.vo.MemberVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(tags = "會員")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class MemberController {

    @Autowired
    private MemberService memberService;


    @ApiOperation("註冊")
    @PostMapping(path = "/register")
    public ResponseEntity<String> register(
                                    HttpServletRequest request, 
                                    @RequestBody MemberVO memberVO) {
        log.info("MemberController ==> register ........... 會員註冊：" + memberVO.toString());
        return memberService.register(request, memberVO);
    }
    @ApiOperation("登入")
    @PostMapping(path = "/login")
    public ResponseEntity<String> memberLogin(HttpServletRequest request, @RequestBody MemberLoginVO memberLogin)
    {
    	if (memberLogin == null) {
            return null;
        }
    	log.info("MemberController ==> memberLogin ........... 會員登入 [" + memberLogin.getEmail() + "]");
        return memberService.memberLogin(request, memberLogin);
    }
    

}
