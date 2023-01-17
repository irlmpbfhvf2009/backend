package com.lwdevelop.backend.Controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lwdevelop.backend.Entity.Member;
import com.lwdevelop.backend.service.MemberService;
import com.lwdevelop.backend.vo.MemberVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(tags = "用戶接口")
@CrossOrigin(origins = "*") 
@RestController
@RequestMapping("/user")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @ApiOperation("註冊")
    @PostMapping(path = "/register")
    public ResponseEntity<ResponseEntity<String>> register(
                                    HttpServletRequest request, 
                                    @RequestBody MemberVO memberVO) throws Exception{
        log.info("MemberController ==> register ........... 會員註冊：{}" , memberVO.toString());
        return ResponseEntity.status(HttpStatus.OK).body(memberService.register(request, memberVO));
    }


    @ApiOperation("用戶信息")
    @PostMapping(path = "/memberInfo")
    public ResponseEntity<ResponseEntity<Member>> memberInfo(
                                    @RequestBody MemberVO memberVO)throws Exception{
    	if (memberVO == null) {
            return null;
        }
        return ResponseEntity.status(HttpStatus.OK).body(memberService.memberInfo(memberVO));
    }

    @ApiOperation("登入")
    @PostMapping(path = "/login")
    public ResponseEntity<ResponseEntity<String>> memberLogin(
                                    HttpServletRequest request, 
                                    @RequestBody MemberVO memberVO)throws Exception{
    	if (memberVO == null) {
            return null;
        }
    	log.info("MemberController ==> memberLogin ........... 會員登入 [ {} ]",memberVO.getEmail());
        return ResponseEntity.status(HttpStatus.OK).body(memberService.memberLogin(request, memberVO));
    }

    

}
