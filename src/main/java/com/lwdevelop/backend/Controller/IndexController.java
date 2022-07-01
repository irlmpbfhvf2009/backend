package com.lwdevelop.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
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

}
