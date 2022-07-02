package com.lwdevelop.backend.service;

import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.lwdevelop.backend.entity.Member;
import com.lwdevelop.backend.repository.MemberRepository;
import com.lwdevelop.backend.security.JwtUtils;
import com.lwdevelop.backend.service.MemberService;
import com.lwdevelop.backend.utils.CommUtils;
import com.lwdevelop.backend.vo.MemberLoginVO;
import com.lwdevelop.backend.vo.MemberVO;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MemberService {
    
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    MemberUserDetailsService memberUserDetailsService;

    public Member findByEmail(String email){
        System.out.println(memberRepository.findByEmail(email));
        return memberRepository.findByEmail(email);
    }
    public Member findByPassword(String password){
        return memberRepository.findByPassword(password);
    }
    public Member findByUsername(String username){
        return memberRepository.findByUsername(username);
    }
    public List<Member> getAllMember(){
        return memberRepository.findAll();
    }
    public Member save(Member member){
        return memberRepository.save(member);
    }
    public Member saveFlush(Member member){
        return memberRepository.saveAndFlush(member);
    }
    
    public ResponseEntity<String> register(HttpServletRequest request, MemberVO memberVO) {
        String email = memberVO.getEmail();
        String password = memberVO.getPassword();
        String username = memberVO.getUsername();

		if (!StringUtils.hasText(email)) {
            log.info("MemberService ==> register ........... [ 信箱不可為空 ]");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("信箱不可為空");
		}
		if (!StringUtils.hasText(password)) {
            log.info("MemberService ==> register ........... [ 密碼不可為空 ]");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("密碼不可為空");
		}
		if (!StringUtils.hasText(username)) {
            log.info("MemberService ==> register ........... [ 用户名不可為空 ]");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("用户名不可為空");
		}
        if(password.length()<7){
            log.info("MemberService ==> register ........... [ 密碼長度不足 ]");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("密碼長度不足");
        }
        if (!email.contains("@")||!email.contains(".com")) {
            log.info("MemberService ==> register ........... [ 信箱格式錯誤 ]");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("信箱格式錯誤");
		}

        log.info("MemberService ==> register ... 檢查會員是否已經存在 [" + email + "]");
		Member memberEmail = findByEmail(email);

		if (memberEmail != null) 
		{
			log.info("MemberService ==> register ... 會員已經存在！");
            log.info("MemberService ==> register ........... [ 用戶名稱已經存在 ]");
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("用戶名稱已經存在");
		}

        // 黑名單檢查
        //String clientIP = CommUtils.getClientIP(request);


        try{
            log.info("MemberService ==> register ... 建立新會員");
            Member member = new Member();
            List<String> roles = Arrays.asList(new String[] { "ROLE_USER" });
            member.setEmail(email);
            member.setPassword(password);
            member.setUsername(username);
            member.setRoles(roles);
            member.setPlatform(CommUtils.getClientDevice(request));
            member.setEnable(true);
            member.setRegIp(CommUtils.getClientIP(request));
            member.setLastLoginIP(CommUtils.getClientIP(request));
            saveFlush(member);
            log.info("MemberService ==> register ... 註冊成功");

        }catch (Exception e){
            log.info("MemberService ==> register ... 註冊失敗 Exception:"+e.toString());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("註冊失敗");
        }

        return ResponseEntity.status(HttpStatus.OK).body("註冊成功");
    }

    /*
     * 會員登入
     */
    public ResponseEntity<String> memberLogin(HttpServletRequest request, @RequestBody MemberLoginVO memberLogin) {
        if (!StringUtils.hasText(memberLogin.getEmail())) 
		{
            log.info("MemberService ==> memberLogin ........... [ 未輸入帳號 ]");
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("未輸入帳號");
		}
        if (!StringUtils.hasText(memberLogin.getPassword())) 
		{
            log.info("MemberService ==> memberLogin ........... [ 未輸入密碼 ]");
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("未輸入密碼");
		}


        Member member = findByEmail(memberLogin.getEmail());
        if(member == null){
            log.info("MemberService ==> memberLogin ........... [ 查無此會員 ]");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("查無此會員");
        }
        if(!member.getEnable()){
            log.info("MemberService ==> memberLogin ........... [ 此帳號被停用 ]");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("此帳號被停用");
        }
        if(!member.getPassword().equals(memberLogin.getPassword())){
            log.info("MemberService ==> memberLogin ........... [ 密碼錯誤 ]");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("密碼錯誤");
        }

        try {

            String userAgent = request.getHeader("User-Agent");

            if (userAgent != null && userAgent.length() > 255) {
                userAgent.substring(0, 255);
            }

            member.setLastLoginIP(CommUtils.getClientIP(request));
            member.setPlatform(CommUtils.getClientDevice(request));
            save(member);
            log.info("MemberService ==> memberLogin ........... [ 登入成功 ]");
            /* memberUserDetailsService.loadUserByUsername(member.getEmail()); */
            JwtUtils jwtToken = new JwtUtils();
            String token = jwtToken.generateToken(member); // 取得token
            System.out.println(token);
            return ResponseEntity.status(HttpStatus.OK).body("登入成功");

        } catch (Exception e) {
            log.info("Member237Service ==> memberLogin Exception: " + e.toString());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("會員登入失敗");
        }
    }

}
