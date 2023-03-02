package com.example.practice.repository;

import com.example.practice.entity.Article;

import java.util.List;
import java.util.Optional;


public interface ArticleRepository {
    Article save(Article article);

    List<Article> findAll();

    Optional<Article> findById(Long id);

    int totalCount();

    List<Article> pagingArticle(int start, int end);
}
