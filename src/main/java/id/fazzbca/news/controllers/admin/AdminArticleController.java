package id.fazzbca.news.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.fazzbca.news.payloads.req.ArticleRequest;
import id.fazzbca.news.services.article.ArticleService;

@RestController
@RequestMapping("/admin/article")
public class AdminArticleController {
    @Autowired
    ArticleService articleService;

    // admin
    // register artikel
    @PostMapping("/register")
    public ResponseEntity<?> createArticle(@RequestBody ArticleRequest request){
        ResponseEntity<?> responseEntity = articleService.addArticleService(request);
        return responseEntity;
    }

    // admin
    // get all article
    @GetMapping
    public ResponseEntity<?> getAllArticles(){
        return articleService.getAllArticleService();
    }

    // admin
    // edit post
    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editArticle(@PathVariable(value = "id") String id, @RequestBody ArticleRequest request){
        return articleService.updateArticleService(id, request);
    }

    // admin
    // edit post
    @PutMapping("/comment/{id}")
    public ResponseEntity<?> commentArticle(@PathVariable(value = "id") String id, @RequestBody ArticleRequest request){
        return articleService.commentArticleService(id, request);
    }
}