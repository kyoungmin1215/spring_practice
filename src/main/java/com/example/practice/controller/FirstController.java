package com.example.practice.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class FirstController {

/*    @GetMapping("/{userName}")
    public String niceToMeetYou(@PathVariable String userName) {
        if(userName.equals("index.html")) {
            return "Unknown";
        }
        return "greetings"; // templates/greetings.mustache -> 브라우저로 전송
    }*/

    @GetMapping("/bye")
    public String seeYouNext(Model model) {
        model.addAttribute("nickname", "가나다");
        return "goodbye";
    }
}
