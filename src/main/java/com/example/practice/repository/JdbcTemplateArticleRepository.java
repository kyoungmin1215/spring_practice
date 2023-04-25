package com.example.practice.repository;

import com.example.practice.entity.Article;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class JdbcTemplateArticleRepository implements ArticleRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateArticleRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Article save(Article article) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("article").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("title", article.getTitle());
        parameters.put("content", article.getContent());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        article.setId(key.longValue());
        return article;
    }

    @Override
    public List<Article> findAll() {
        return jdbcTemplate.query("select * from article order by id desc", articleRowMapper());
    }

    @Override
    public Optional<Article> findById(Long id) {
        List<Article> result = jdbcTemplate.query("select * from article where id = ?", articleRowMapper(), id);
        return result.stream().findAny();
    }

    @Override
    public int totalCount() {
        return findAll().size();
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

        System.out.println(findAll());
        return new ArrayList<>(findAll().subList(start-1, end));
    }

    private RowMapper<Article> articleRowMapper() {
        return (rs, rowNum) -> {
            Article article = new Article();
            article.setId(rs.getLong("id"));
            article.setTitle(rs.getString("title"));
            article.setContent(rs.getString("content"));
            return article;
        };
    }
}
