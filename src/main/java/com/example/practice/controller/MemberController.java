package com.example.practice.controller;

import com.example.practice.dto.MemberForm;
import com.example.practice.entity.Member;
import com.example.practice.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(MemberForm memberForm) {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm memberForm) {
        Member member = new Member();
        member.setUserId(memberForm.getUserId());
        member.setUserName(memberForm.getUserName());
        member.setUserPwd(memberForm.getUserPwd());

        memberService.join(member);

        return "greetings";
    }

    @GetMapping("/members/memberList")
    public String memberList(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);

        return "members/memberList";
    }
}
