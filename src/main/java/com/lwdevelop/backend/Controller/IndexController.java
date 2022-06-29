package com.lwdevelop.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Index接口")
@Controller
public class IndexController {

    @ApiOperation("跳轉index.html")
    @RequestMapping("/")
    public String Index() {
        return "index"; // 这个就显示跳转到了index.html, 注意是templates下面的
    }
}