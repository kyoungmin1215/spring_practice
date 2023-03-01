package com.example.practice.config;

import com.example.practice.repository.ArticleRepository;
import com.example.practice.repository.MemberRepository;
import com.example.practice.repository.MemoryArticleRepository;
import com.example.practice.repository.MemoryMemberRepository;
import com.example.practice.service.ArticleService;
import com.example.practice.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public ArticleRepository articleRepository() {
        return new MemoryArticleRepository();
    }

    @Bean
    public ArticleService articleService() {
        return new ArticleService(articleRepository());
    }

}
