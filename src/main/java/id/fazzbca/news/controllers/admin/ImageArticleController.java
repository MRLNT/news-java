package id.fazzbca.news.controllers.admin;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import id.fazzbca.news.services.storageBook.StorageBookService;

@RestController
public class ImageArticleController {
    @Autowired
    StorageBookService storageBookService;

    @PostMapping("/admin/files/article")
    public ResponseEntity<?> storeImage(@RequestParam(value = "file") MultipartFile file, @RequestParam(value = "articleId") String articleId) throws IOException{
        return storageBookService.storeImage(file, articleId);
    }

    @GetMapping("/files/article/{imageId}")
    public ResponseEntity<?> loadImage(@PathVariable String imageId){
        return storageBookService.loadImage(imageId);
    }
}
