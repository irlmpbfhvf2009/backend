package com.lwdevelop.backend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.lwdevelop.backend.entity.Member;
import com.lwdevelop.backend.security.JwtUtils;
import com.lwdevelop.backend.service.MemberService;
import com.lwdevelop.backend.service.MemberUserDetailsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Index接口")
@Controller
@CrossOrigin(origins = "*")
public class IndexController {

    @ApiOperation("跳轉首頁")
    @RequestMapping("/")
    public String Index() {
        return "index"; // templates下面的index.html
    }


    @ApiOperation("登入")
    @RequestMapping("/logina")
    public String Login() {
        return "logina"; // templates下面的index.html
    }
    @ApiOperation("測試AUTH")
    @RequestMapping("/auth")
    public String auth() {
        String a =SecurityContextHolder.getContext().getAuthentication().toString();
        System.out.println("auth="+a);
        return "logina"; // templates下面的index.html
    }
    @Autowired
    MemberService memberService;
    @Autowired
    MemberUserDetailsService memberUserDetailsService;

    @ApiOperation("簽發token")
    @GetMapping("/atoken")
    public ResponseEntity<String> atoken(@RequestParam("email") String email) {
        Member m = memberService.findByEmail(email); 
        JwtUtils jwtToken = new JwtUtils();
        String token = jwtToken.generateToken(m); // 取得token
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }
    @ApiOperation("驗證token")
    @GetMapping("/btoken")
    public ResponseEntity<String> btoken(@RequestHeader("Authorization") String au) {
        String token = au;
        JwtUtils jwtToken = new JwtUtils();
        jwtToken.getUserNameFromJwtToken(token);
        System.out.println(jwtToken.getUserNameFromJwtToken(token));
    return ResponseEntity.status(HttpStatus.OK).body("Hello CaiLi");

    }

}
