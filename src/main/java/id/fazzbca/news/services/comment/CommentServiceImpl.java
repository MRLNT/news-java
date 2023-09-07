package id.fazzbca.news.services.comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import id.fazzbca.news.models.Article;
import id.fazzbca.news.models.Comment;
import id.fazzbca.news.models.User;
import id.fazzbca.news.payloads.req.CommentRequest;
import id.fazzbca.news.payloads.res.ResponseHandler;
import id.fazzbca.news.repositories.ArticleRepositories;
import id.fazzbca.news.repositories.RoleRepository;
import id.fazzbca.news.repositories.CommentRepository;
import id.fazzbca.news.repositories.UserRepository;

@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    ArticleRepositories articleRepositories;

    @Autowired
    CommentRepository saveRepository;

    @Override
    public ResponseEntity<?> addCommentArticleService(CommentRequest request) {
        // cek inputan
        if (request.getArticle() == null || request.getArticle().isEmpty()) {
            throw new IllegalArgumentException("artikel dibutuhkan");
        }
    
        // dapatin artikel
        Article article = articleRepositories.findByTitle(request.getArticle());
        // cek artikel
        if (article == null) {
            throw new IllegalArgumentException("artikel tidak ditemukan");
        }
        // dapetin user
        User user = userRepository.findByUsername(request.getUser());
        if (user == null) {
            throw new IllegalArgumentException("user tidak ditemukan");
        }

    
        // Simpan komentar pada artikel
        Comment comment = new Comment(article, request.getName(), user); // Assuming 'name' should be used as the comment
        saveRepository.save(comment);
    
        return ResponseHandler.responseMessage(201, "comment pada artikel berhasil didaftarkan", true);
    }
    

    @Override
    public ResponseEntity<?> getAllCommentArticle() {
        List<Comment> saves = saveRepository.findAll();
        return ResponseHandler.responseData(200, "success", saves);
    }
    
}
