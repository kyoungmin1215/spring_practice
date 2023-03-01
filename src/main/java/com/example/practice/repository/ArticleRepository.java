package com.example.practice.repository;

import com.example.practice.entity.Article;

import java.util.List;


public interface ArticleRepository {
    Article save(Article article);

    List<Article> findAll();

    int totalCount();
}
