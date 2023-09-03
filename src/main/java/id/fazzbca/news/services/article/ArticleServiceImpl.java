package id.fazzbca.news.services.article;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.fazzbca.news.payloads.req.ArticleRequest;
import id.fazzbca.news.models.Article;
import id.fazzbca.news.models.Category;
import id.fazzbca.news.models.Role;
import id.fazzbca.news.models.User;
import id.fazzbca.news.payloads.res.ResponseHandler;
import id.fazzbca.news.repositories.ArticleRepositories;
import id.fazzbca.news.repositories.CategoryRepository;
import id.fazzbca.news.repositories.RoleRepository;
import id.fazzbca.news.repositories.UserRepository;

@Service
public class ArticleServiceImpl implements ArticleService{
    @Autowired
    ArticleRepositories articleRepositories;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    RoleRepository roleRepository;

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
        Article article = new Article(request.getTitle(), request.getContent(), user, category, "");

        // simpan role
        articleRepositories.save(article);

        return ResponseHandler.responseMessage(201, "artikel berhasil ditambahkan", true);
    }

    @Override
    public ResponseEntity<?> getAllArticleService() {
        List<Article> articles = articleRepositories.findAll();
        
        return ResponseHandler.responseData(200, "success", articles);
    }

    @Override
    public ResponseEntity<?> updateArticleService(String id, ArticleRequest request) {
        // find data by id
        Article article = articleRepositories.findById(id).orElseThrow(() -> {
            throw new NoSuchElementException("id artikel tidak ditemukan");
        });

        // Validasi role user
        String username = request.getUser();
        User user = userRepository.findByUsername(username);
        Role userRole = user.getRole();
        String roleName = userRole.getName();
        if ("creator".equals(roleName)) {
            throw new IllegalArgumentException("Creator tidak diizinkan untuk mengedit artikel");
        }

        // update artikel
        if (request.getTitle() != "") {
            article.setTitle(request.getTitle());
        }
        if (request.getContent() != "") {
            article.setContent(request.getContent());
        }

        // save ke db
        articleRepositories.save(article);

        // return response
        return ResponseHandler.responseMessage(200, "Edit berhas, Role: " + roleName, true);
    }

    @Override
    public ResponseEntity<?> commentArticleService(String id, ArticleRequest request) {
        // find data by id
        Article article = articleRepositories.findById(id).orElseThrow(() -> {
            throw new NoSuchElementException("id artikel tidak ditemukan");
        });

        // Validasi role user
        if (request.getComment() != "") {
            article.setComment(request.getComment());
        }
        // save ke db
        articleRepositories.save(article);

        // return response
        return ResponseHandler.responseMessage(200, "Comment artikel berhasil", true);
    }
    
}