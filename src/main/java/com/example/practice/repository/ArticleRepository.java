package com.example.practice.repository;

import com.example.practice.entity.Article;
import org.springframework.data.repository.CrudRepository;

// CrudRepository<관리대상Entity, Entitiy대표값의 type>
public interface ArticleRepository extends CrudRepository<Article, Long> {
}
