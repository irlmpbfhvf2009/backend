package com.lwdevelop.backend.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.lwdevelop.backend.entity.Member;
import com.lwdevelop.backend.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(tags = "管理員接口")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    
    @ApiOperation("查詢用戶(信箱)")
    @PostMapping(path = "/findByEmail")
    public ResponseEntity<Member> findByEmail(
                                    HttpServletRequest request, 
                                    @RequestParam("email") String email) throws Exception{
        log.info("AdminController ==> findByEmail ........... 查詢信箱：" + email);
        return ResponseEntity.status(HttpStatus.OK).body(adminService.findByEmail(email));
    }
    @ApiOperation("查詢用戶(用戶名)")
    @PostMapping(path = "/findByUsername")
    public ResponseEntity<Member> findByUsername(
                                    HttpServletRequest request, 
                                    @RequestParam("username") String username) throws Exception{
        log.info("AdminController ==> findByEmail ........... 查詢信箱：" + username);
        return ResponseEntity.status(HttpStatus.OK).body(adminService.findByUsername(username));
    }
    @ApiOperation("刪除用戶(信箱)")
    @PostMapping(path = "/deleteByEmail")
    public ResponseEntity<String> deleteByEmail(
                                    HttpServletRequest request, 
                                    @RequestParam("email") String email) throws Exception{
        log.info("AdminController ==> findByEmail ........... 查詢信箱：" + email);
        return ResponseEntity.status(HttpStatus.OK).body(adminService.deleteByEmail(email));
    }
    @ApiOperation("刪除用戶(用戶名)")
    @PostMapping(path = "/deleteByUsername")
    public ResponseEntity<String> deleteByUsername(
                                    HttpServletRequest request, 
                                    @RequestParam("username") String username) throws Exception{
        log.info("AdminController ==> findByEmail ........... 查詢信箱：" + username);
        return ResponseEntity.status(HttpStatus.OK).body(adminService.deleteByUsername(username));
    }
}
