package com.lwdevelop.backend.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lwdevelop.backend.entity.Admin;
import com.lwdevelop.backend.repository.AdminRepository;
import com.lwdevelop.backend.service.AdminService;
import com.lwdevelop.backend.service.MemberService;
import com.lwdevelop.backend.utils.CommUtils;
import com.lwdevelop.backend.vo.AdminLoginVO;
import com.lwdevelop.backend.vo.AdminVO;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(tags = "測試接口")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/test")
public class TestController {
    @Autowired
    private MemberService memberService;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private AdminService adminService;


    public Admin saveFlush(Admin admin){
        return adminRepository.saveAndFlush(admin);
    }

    @GetMapping("/test")
    public ResponseEntity<UserDetails> test(@RequestParam("email") String email) {

        UserDetails a = memberService.loadUserByUsername(email);
        log.info("a="+a);
        return ResponseEntity.ok(a);
    }
    @PostMapping("/adminregister")
    public ResponseEntity<String> adminregister(HttpServletRequest request,@RequestBody AdminVO adminVO) {

        try{
            Admin admin = new Admin();
            List<String> roles = Arrays.asList(new String[] { "ADMIN" });
            admin.setEmail(adminVO.getEmail());
            admin.setPassword(adminVO.getPassword());
            admin.setUsername(adminVO.getUsername());
            admin.setRoles(roles);
            admin.setPlatform(CommUtils.getClientDevice(request));
            admin.setEnable(true);
            admin.setRegIp(CommUtils.getClientIP(request));
            admin.setLastLoginIP(CommUtils.getClientIP(request));

            System.out.println("adminVO="+adminVO);
            System.out.println("admin="+admin);
            saveFlush(admin);
            log.info("註冊成功");

        }catch (Exception e){
            log.info("MemberService ==> register ... 註冊失敗 Exception:"+e.toString());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("註冊失敗");
        }
        return ResponseEntity.status(HttpStatus.OK).body("註冊成功");
    }    
    public Admin findByEmail(String email){
        return adminRepository.findByEmail(email);
    }

    @PostMapping("/adminlogin")
    public ResponseEntity<String> adminLogin(HttpServletRequest request, AdminLoginVO adminLogin) {
        Admin admin = findByEmail(adminLogin.getEmail());
        if(admin == null){
            log.info(" [ 查無此會員 ]");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("查無此會員");
        }
        if(!admin.getEnable()){
            log.info(" [ 此帳號被停用 ]");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("此帳號被停用");
        }
        if(!admin.getPassword().equals(adminLogin.getPassword())){
            log.info("[ 密碼錯誤 ]");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("密碼錯誤");
        }

        try {

            adminService.loadUserByUsername(admin.getEmail());
            System.out.println(adminService.loadUserByUsername(admin.getEmail()));
            log.info("[ 登入成功 ]");
            return ResponseEntity.status(HttpStatus.OK).body("登入成功");

        } catch (Exception e) {
            log.info("Member237Service ==> memberLogin Exception: " + e.toString());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("會員登入失敗");
        }
    }

}
