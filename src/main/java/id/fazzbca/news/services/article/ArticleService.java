package id.fazzbca.news.services.article;

import org.springframework.http.ResponseEntity;

import id.fazzbca.news.payloads.req.ArticleRequest;

public interface ArticleService {
    // create article
    ResponseEntity<?> addArticleService(ArticleRequest request);

    // get all article
    ResponseEntity<?> getAllArticleService();
}