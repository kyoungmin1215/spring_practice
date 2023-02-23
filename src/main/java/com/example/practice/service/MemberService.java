package com.example.practice.service;

import com.example.practice.entity.Member;
import com.example.practice.repository.MemberRepository;

import java.util.List;

public class MemberService {

    private MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void join(Member member) {
        validateDuplicateMemberId(member);
        memberRepository.save(member);
    }

    private void validateDuplicateMemberId(Member member) {
        memberRepository.findByUserId(member.getUserId()).ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 아이디 입니다.");
        });
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }



}
