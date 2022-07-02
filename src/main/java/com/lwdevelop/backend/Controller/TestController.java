package com.lwdevelop.backend.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Api(tags = "用戶接口")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/test")
public class TestController {
/*     @Autowired
    private MemberService memberService; */

    @ApiOperation("測試getAuthentication()")
    @PostMapping(path = "/authentication")
    public ResponseEntity<String> register(
        UserDetails u,
        HttpServletRequest request, 
        @RequestBody String memberVO) throws Exception{
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
        
    @PostMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.status(HttpStatus.OK).body("Hello");
      }
}
