package id.fazzbca.news.controllers.guest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.fazzbca.news.payloads.req.ArticleRequest;
import id.fazzbca.news.services.article.ArticleService;

@RestController
@RequestMapping("/guest/article")
public class GuestArticleController {
    @Autowired
    ArticleService articleService;

    // guest
    // get all article
    // lupa PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<?> getAllArticles(){
        return articleService.getAllArticleService();
    }

    // guest
    // edit post
    @PutMapping("/comment/{id}")
    public ResponseEntity<?> commentArticle(@PathVariable(value = "id") String id, @RequestBody ArticleRequest request){
        return articleService.commentArticleService(id, request);
    }
}