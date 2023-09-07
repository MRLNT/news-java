package id.fazzbca.news.services.comment;

import org.springframework.http.ResponseEntity;

import id.fazzbca.news.payloads.req.CommentRequest;

public interface CommentService {
     // create user
    ResponseEntity<?> addCommentArticleService(CommentRequest request);

    // get all user
    ResponseEntity<?> getAllCommentArticle();
}
