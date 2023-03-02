package com.example.practice.service;

import com.example.practice.entity.Article;
import com.example.practice.repository.ArticleRepository;

import java.util.List;

public class ArticleService {
    private ArticleRepository articleRepository;

    public ArticleService(ArticleRepository repository) {
        this.articleRepository = repository;
    }

    public void create(Article article) {
        articleRepository.save(article);
    }

    public List<Article> findAllArticles() {
        return articleRepository.findAll();
    }

    public int totalArticlesCount() {
        return articleRepository.totalCount();
    }

    public List<Article> rangeArticles(int start, int end) {
        return articleRepository.pagingArticle(start, end);
    }
}
