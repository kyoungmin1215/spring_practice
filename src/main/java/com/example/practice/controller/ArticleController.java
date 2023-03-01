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

import javax.servlet.http.HttpServletRequest;
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
    public String articleList(Model model, HttpServletRequest request) {
        int page = 1;
        int limit = 5;
        int totalCount = articleService.totalArtciclesCount();

        if(request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        int startRow = (page - 1) * limit + 1;
        int endRow = startRow + limit - 1;

        int maxPage = (int)((double)totalCount / limit + 0.95);
        int startPage = (((int)((double)page / 10 + 0.9))- 1) * 10 + 1;
        int endPage = maxPage;

        if(endPage > startPage + 10 - 1)
            endPage = startPage + 10 - 1;

        List<Article> articles = articleService.findAllArticles();
        model.addAttribute("articles", articles);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("maxPage", maxPage);
        model.addAttribute("page", page);

        return "articles/list";
    }
}
