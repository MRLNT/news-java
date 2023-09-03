package id.fazzbca.news.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import id.fazzbca.news.models.Article;

public interface ArticleRepositories extends JpaRepository<Article, String>{
    Article findByTitle(String title);
}