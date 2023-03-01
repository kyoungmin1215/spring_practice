package com.example.practice.controller;

import com.example.practice.dto.ArticleForm;
import com.example.practice.entity.Article;
import com.example.practice.repository.ArticleRepository;
import com.example.practice.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.FileNotFoundException;
import java.util.List;

@Controller
@Slf4j // 로깅을 위한 어노테이션
public class ArticleController {

    private ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/articles/new")
    public String newArticleForm(ArticleForm form) {
        return "articles/new";
    }

    @PostMapping("/articles/new")
    public String createArticle(ArticleForm form) {
        Article article = new Article();
        article.setTitle(form.getTitle());
        article.setContent(form.getContent());
        articleService.create(article);

        return "redirect:/articles/list";
    }

    @GetMapping("/articles/list")
    public String articleList(Model model) {
        List<Article> articles = articleService.findAllArticles();
        model.addAttribute("articles", articles);
        return "articles/list";
    }
}
