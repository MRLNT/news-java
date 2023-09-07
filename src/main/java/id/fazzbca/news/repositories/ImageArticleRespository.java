package id.fazzbca.news.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import id.fazzbca.news.models.ImageArticle;

public interface ImageArticleRespository extends JpaRepository<ImageArticle, String> {

}