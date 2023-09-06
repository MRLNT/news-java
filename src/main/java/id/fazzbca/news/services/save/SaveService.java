package id.fazzbca.news.services.save;

import org.springframework.http.ResponseEntity;

import id.fazzbca.news.payloads.req.SaveRequest;

public interface SaveService {
     // create user
    ResponseEntity<?> addSaveArticleService(SaveRequest request);

    // get all user
    ResponseEntity<?> getAllSavedArticle();
}
