package id.fazzbca.news.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.fazzbca.news.payloads.req.SaveRequest;
import id.fazzbca.news.services.save.SaveService;

@RestController
@RequestMapping("/save")
public class SaveController {
    @Autowired
    SaveService saveService;

    @PostMapping("/article")
    public ResponseEntity<?> createSave(@RequestBody SaveRequest request){
        ResponseEntity<?> responseEntity = saveService.addSaveArticleService(request);
        return responseEntity;
    }

    @GetMapping
    public ResponseEntity<?> getAllSaved(){
        return saveService.getAllSavedArticle();
    }
}