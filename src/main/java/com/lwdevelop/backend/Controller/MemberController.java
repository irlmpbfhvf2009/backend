package com.lwdevelop.backend.controller;

import java.util.HashMap;

import javax.security.auth.message.AuthException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lwdevelop.backend.security.JWTUtil;
import com.lwdevelop.backend.service.MemberService;
import com.lwdevelop.backend.vo.MemberLoginVO;
import com.lwdevelop.backend.vo.MemberVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(tags = "用戶接口")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/user")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @ApiOperation("註冊")
   /*  @ApiImplicitParams({
        @ApiImplicitParam(name = "memberVO", value = "用戶實體類", paramType = "body", dataType = "MemberVO", required = true)}) */
    @PostMapping(path = "/register")
    public ResponseEntity<ResponseEntity<String>> register(
                                    HttpServletRequest request, 
                                    @RequestBody MemberVO memberVO) throws Exception{
        log.info("MemberController ==> register ........... 會員註冊：" + memberVO.toString());
        return ResponseEntity.status(HttpStatus.OK).body(memberService.register(request, memberVO));
    }

    @PostMapping("/token")
    public ResponseEntity<String> token(@RequestBody HashMap <String, String> user) {
        JWTUtil jwtToken = new JWTUtil();
        String token = jwtToken.generateToken(user); // 取得token
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }
    @PostMapping("/hello")
    public ResponseEntity<String> hello(@RequestHeader("Authorization") String au,@RequestBody HashMap <String, String> user) {
        JWTUtil jwtToken = new JWTUtil();
        String token = jwtToken.generateToken(user); // 取得token
        System.out.println("token="+token);
        try {
            jwtToken.validateToken(token);
        } catch (AuthException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body("Hello CaiLi");
      }

    @ApiOperation("登入")
/*     @ApiImplicitParams({
        @ApiImplicitParam(name = "memberLogin", value = "用戶實體類", paramType = "body", dataType = "MemberLoginVO", required = true)}) */
    @PostMapping(path = "/login")
    public ResponseEntity<ResponseEntity<String>> memberLogin(HttpServletRequest request, 
                                    @RequestBody MemberLoginVO memberLogin)throws Exception{
    	if (memberLogin == null) {
            return null;
        }
        
    	log.info("MemberController ==> memberLogin ........... 會員登入 [" + memberLogin.getEmail() + "]");
        return ResponseEntity.status(HttpStatus.OK).body(memberService.memberLogin(request, memberLogin));
    }
    

}
