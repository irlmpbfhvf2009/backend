package com.lwdevelop.backend.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class indexController {
    
    @RequestMapping("/")
    public String index(){
        return "hello";
    }
}
