package id.fazzbca.news.services.storageBook;

import java.io.IOException;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import id.fazzbca.news.models.Article;
import id.fazzbca.news.models.ImageArticle;
import id.fazzbca.news.payloads.res.ResponseHandler;
import id.fazzbca.news.repositories.ArticleRepositories;
import id.fazzbca.news.repositories.ImageArticleRespository;

@Service
public class StorageBookServiceImpl implements StorageBookService{
    @Autowired
    ImageArticleRespository imageArticleRespository;

    @Autowired
    ArticleRepositories articleRepositories;

    @Override
    public ResponseEntity<?> storeImage(MultipartFile file, String articleId) throws IOException {
        // ambil nama gambar
        String imgName = StringUtils.cleanPath(file.getOriginalFilename());
        // file content type -> untuk ambnil type filenya

        // cari entitas buku
        Article article = articleRepositories.findById(articleId).orElseThrow(() -> new NoSuchElementException("Artikel tidak ditemukan"));

        // buatkan entitas image article
        ImageArticle imageArticle = new ImageArticle(imgName, file.getBytes(), article);

        // menyimpan 
        imageArticleRespository.save(imageArticle);

        // buatkan sharedUrl
        // endpoint untuk upload: /admin/files/book -> POST
        // endpoint untuk load: /files/book/{uuidGambar} ->GET
        String sharedUrl = ServletUriComponentsBuilder
            .fromCurrentContextPath() // localhost:9090
            .path("/files/book")
            .path(imageArticle.getId()) // id gambar
            .toUriString();
        
        // set shared url ke obj image article
        imageArticle.setSharedUrl(sharedUrl);
        imageArticleRespository.save(imageArticle);

        return ResponseHandler.responseData(201, "Berhasil upload gambar", imageArticle);
    }

    @Override
    public ResponseEntity<?> loadImage(String imageId) {
        ImageArticle imageArticle = imageArticleRespository.findById(imageId).orElseThrow(() -> new NoSuchElementException("Gambar tidak ditemukan"));
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + imageArticle.getImageName() + "\"")
            .body(imageArticle.getData());
    }
    
}
