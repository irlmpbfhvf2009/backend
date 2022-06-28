package com.lwdevelop.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @RequestMapping("/")
    public String Index() {
        return "index"; // 这个就显示跳转到了index.html, 注意是templates下面的
    }
}
