package com.example.springcloud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AritileController {

    @GetMapping("/artilelist")
    public String index() {
        return "artile";
    }
}
