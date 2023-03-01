package com.example.practice.repository;

import com.example.practice.entity.Article;
import com.example.practice.entity.Member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoryArticleRepository implements ArticleRepository {
    private static Map<Long, Article> articleStore = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Article save(Article article) {
        article.setId(++sequence);
        articleStore.put(article.getId(), article);
        return article;
    }

    @Override
    public List<Article> findAll() {
        return new ArrayList<>(articleStore.values());
    }
}
