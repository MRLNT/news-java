package id.fazzbca.news.services.save;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import id.fazzbca.news.models.Article;
import id.fazzbca.news.models.Save;
import id.fazzbca.news.payloads.req.SaveRequest;
import id.fazzbca.news.payloads.res.ResponseHandler;
import id.fazzbca.news.repositories.ArticleRepositories;
import id.fazzbca.news.repositories.RoleRepository;
import id.fazzbca.news.repositories.SaveRepository;
import id.fazzbca.news.repositories.UserRepository;

@Service
public class SaveServiceImpl implements SaveService{
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    ArticleRepositories articleRepositories;

    @Autowired
    SaveRepository saveRepository;

    @Override
    public ResponseEntity<?> addSaveArticleService(SaveRequest request) {
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
    
        // Simpan komentar pada artikel
        Save save = new Save(article, request.getName()); // Assuming 'name' should be used as the comment
        saveRepository.save(save);
    
        return ResponseHandler.responseMessage(201, "comment pada artikel berhasil didaftarkan", true);
    }
    

    @Override
    public ResponseEntity<?> getAllSavedArticle() {
        List<Save> saves = saveRepository.findAll();
        return ResponseHandler.responseData(200, "success", saves);
    }
    
}
