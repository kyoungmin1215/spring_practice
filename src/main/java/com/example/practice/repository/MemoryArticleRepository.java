package com.example.practice.repository;

import com.example.practice.entity.Article;

import java.util.*;

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

    @Override
    public Optional<Article> findById(Long id) {
        return Optional.ofNullable(articleStore.get(id));
    }

    @Override
    public int totalCount() {
        return articleStore.size();
    }

    @Override
    public List<Article> pagingArticle(int start, int end) {
        if(totalCount() == 0) {
            return findAll();
        }

        if(end > totalCount()) {
            end = totalCount();
        }

        if(start < 1 || start > end) {
            return null;
        }

        return new ArrayList<>(findAll().subList(start-1, end));

    }


}
