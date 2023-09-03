package id.fazzbca.news.services.article;

import org.springframework.http.ResponseEntity;

import id.fazzbca.news.payloads.req.ArticleRequest;

public interface ArticleService {
    // create article
    ResponseEntity<?> addArticleService(ArticleRequest request);

    // get all article
    ResponseEntity<?> getAllArticleService();

    // update article
    ResponseEntity<?> updateArticleService(String id, ArticleRequest request);

    // update comment
    ResponseEntity<?> commentArticleService(String id, ArticleRequest request);
}