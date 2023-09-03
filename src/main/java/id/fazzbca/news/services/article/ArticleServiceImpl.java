package id.fazzbca.news.services.article;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.fazzbca.news.payloads.req.ArticleRequest;
import id.fazzbca.news.models.Article;
import id.fazzbca.news.models.Category;
import id.fazzbca.news.models.User;
import id.fazzbca.news.payloads.res.ResponseHandler;
import id.fazzbca.news.repositories.ArticleRepositories;
import id.fazzbca.news.repositories.CategoryRepository;
import id.fazzbca.news.repositories.UserRepository;

@Service
public class ArticleServiceImpl implements ArticleService{
    @Autowired
    ArticleRepositories articleRepositories;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public ResponseEntity<?> addArticleService(ArticleRequest request) {
        // dapetin user nya
        User user = userRepository.findByUsername(request.getUser());
        // cek user
        if (user == null) {
            throw new IllegalArgumentException("User tidak ditemukan");
        }
        // dapetin categori nya
        Category category = categoryRepository.findByName(request.getCategory());
        // cek categori
        if (category == null) {
            throw new IllegalArgumentException("Categori tidak ditemukan");
        }

        // cek inputan
        if (request.getTitle() == null || request.getTitle() == "") {
            throw new IllegalArgumentException("judul dibutuhkan");
        }

        // convert request menjadi model entity
        Article article = new Article(request.getTitle(), request.getContent(), user, category);

        // simpan role
        articleRepositories.save(article);

        return ResponseHandler.responseMessage(201, "artikel berhasil ditambahkan", true);
    }

    @Override
    public ResponseEntity<?> getAllArticleService() {

        try {
            List<Article> articles = articleRepositories.findAll();
            return ResponseHandler.responseData(200, "success", articles);
        } catch (Exception e) {
            return ResponseHandler.responseError(500, e.getMessage(), null);
        }
    }
}