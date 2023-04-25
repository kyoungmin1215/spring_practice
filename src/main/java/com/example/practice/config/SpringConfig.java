package com.example.practice.config;

import com.example.practice.repository.*;
import com.example.practice.service.ArticleService;
import com.example.practice.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private DataSource dataSource;

    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

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

//        return new MemoryArticleRepository();
        return new JdbcTemplateArticleRepository(dataSource);
    }

    @Bean
    public ArticleService articleService() {
        return new ArticleService(articleRepository());
    }

}
