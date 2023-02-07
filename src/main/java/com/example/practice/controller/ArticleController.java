package com.example.practice.controller;

import com.example.practice.dto.ArticleForm;
import com.example.practice.entity.Article;
import com.example.practice.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.yaml.snakeyaml.Yaml;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Map;

@Controller
public class ArticleController {

    @Autowired // DI, 스프링 부트가 미리 생성해놓은 객체를 가져다가 자동 연결
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticleForm() throws FileNotFoundException {
        return "articles/new";
    }


    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) {
        System.out.println(form.toString());

        // DTO를 변환 -> Entity
        Article article = form.toEntity();

        System.out.println(article.toString());

        // Repository에게 Entity를 DB안에 저장
        Article saved = articleRepository.save(article);
        System.out.println(saved.toString());
        return "";
    }
}
