package com.example.practice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FinanceController {

    @GetMapping("showFinance")
    public String showFinance() {
        return "finance/showFinance";
    }
}
