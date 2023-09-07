package id.fazzbca.news.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.fazzbca.news.payloads.req.CommentRequest;
import id.fazzbca.news.services.comment.CommentService;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    CommentService saveService;

    @PostMapping("/add")
    public ResponseEntity<?> createComment(@RequestBody CommentRequest request){
        ResponseEntity<?> responseEntity = saveService.addCommentArticleService(request);
        return responseEntity;
    }

    @GetMapping
    public ResponseEntity<?> getAllSaved(){
        return saveService.getAllCommentArticle();
    }
}