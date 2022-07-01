package com.lwdevelop.backend.controller;

import java.util.HashMap;

import javax.security.auth.message.AuthException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lwdevelop.backend.security.JWTUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Index接口")
@Controller
public class IndexController {

    @ApiOperation("跳轉首頁")
    @RequestMapping("/")
    public String Index() {
        return "index"; // templates下面的index.html
    }
    @PostMapping("/test/token")
    public ResponseEntity<String> token(@RequestBody HashMap <String, String> user) {
        JWTUtil jwtToken = new JWTUtil();
        String token = jwtToken.generateToken(user); // 取得token
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }
    @PostMapping("/test/hello")
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

}
