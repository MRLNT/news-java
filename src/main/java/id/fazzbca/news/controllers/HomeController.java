package id.fazzbca.news.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.fazzbca.news.services.article.ArticleService;

@RestController
@RequestMapping("/home")
public class HomeController {
    @Autowired
    ArticleService articleService;

    // get all article
    @GetMapping
    public ResponseEntity<?> getAllArticles(){
        return articleService.getAllArticleService();
    }
}