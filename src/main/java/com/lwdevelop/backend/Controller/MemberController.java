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
import com.lwdevelop.backend.utils.JwtTokenUtils;
import com.lwdevelop.backend.vo.MemberLoginVO;
import com.lwdevelop.backend.vo.MemberVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(tags = "用戶接口")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
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
        return ResponseEntity.ok(memberService.register(request, memberVO));
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
        JwtTokenUtils jwtToken = new JwtTokenUtils();
        String token = jwtToken.generateToken(memberLogin);
    	log.info("MemberController ==> memberLogin ........... 會員登入 [" + memberLogin.getEmail() + "]");
        return ResponseEntity.ok(memberService.memberLogin(request, memberLogin));
    }
    

}
