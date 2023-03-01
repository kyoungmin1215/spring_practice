package com.example.practice.service;

import com.example.practice.entity.Article;
import com.example.practice.repository.ArticleRepository;

import java.util.List;

public class ArticleService {
    private ArticleRepository repository;

    public ArticleService(ArticleRepository repository) {
        this.repository = repository;
    }

    public void create(Article article) {
        repository.save(article);
    }

    public List<Article> findAllArticles() {
        return repository.findAll();
    }
}
