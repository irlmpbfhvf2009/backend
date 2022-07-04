package com.lwdevelop.backend.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lwdevelop.backend.service.MemberService;
import com.lwdevelop.backend.vo.MemberVO;
import com.lwdevelop.backend.vo.SearchFriendVO;
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

    @ApiOperation("搜尋好友")
    @PostMapping(path = "/searchFriend")
    public ResponseEntity<ResponseEntity<List<String>>> searchFriend(@RequestBody SearchFriendVO searchFriendVO)throws Exception{
    	log.info("MemberController ==> searchFriend ........... 搜尋好友 [ {} ]",searchFriendVO.getUsername());
        return ResponseEntity.status(HttpStatus.OK).body(memberService.searchFriend(searchFriendVO));
    }

    @ApiOperation("新增好友")
    @PostMapping(path = "/addFriend")
    public ResponseEntity<ResponseEntity<String>> addFriend(@RequestBody MemberVO memberVO)throws Exception{
    	log.info("MemberController ==> addFriend ........... 新增好友 [ {} 加入 {} ]",memberVO.getLoginEmail(),memberVO.getEmail());
        return ResponseEntity.status(HttpStatus.OK).body(memberService.addFriend(memberVO));
    }

    @ApiOperation("我的好友")
    @PostMapping(path = "/myFriend")
    public ResponseEntity<ResponseEntity<List<String>>> myFriend(HttpServletRequest request, 
                                                    @RequestBody MemberVO memberVO)throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(memberService.myFriend(memberVO));
    }
    

}
