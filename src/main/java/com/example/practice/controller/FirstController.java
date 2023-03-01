package com.example.practice.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class FirstController {

    @GetMapping("/greetings")
    public String tempIndex() {
        return "greetings";
    }
}
