package id.fazzbca.news.services.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import id.fazzbca.news.models.Category;
// import id.fazzbca.news.models.User;
import id.fazzbca.news.payloads.req.CategoryRequest;
import id.fazzbca.news.payloads.res.ResponseHandler;
import id.fazzbca.news.repositories.CategoryRepository;
import id.fazzbca.news.repositories.UserRepository;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public ResponseEntity<?> addCategoryService(CategoryRequest request) {
        // // Validasi role user biasa tidak bisa add article
        // String usernameRole = request.getUser();
        // User userRole = userRepository.findByUsername(usernameRole);
        // Role userRoleName = userRole.getRole();
        // String roleName = userRoleName.getName();
        // if ("user".equals(roleName)) {
        //     throw new IllegalArgumentException("User tidak diizinkan untuk mengedit artikel");
        // }
        // FIX THIS LATER
        // dapetin user nya
        // User user = userRepository.findByUsername(request.getUser());
        // // cek user
        // if (user == null) {
        //     throw new IllegalArgumentException("User tidak ditemukan");
        // }

        // cek inputan
        if (request.getName() == null || request.getName() == "") {
            throw new IllegalArgumentException("kategori dibutuhkan");
        }
        // convert request menjadi model entity
        // Category category = new Category(request.getName(), user);
        Category category = new Category(request.getName());

        // simpan role
        categoryRepository.save(category);

        return ResponseHandler.responseMessage(201, "kategori berhasil ditambahkan", true);
    }

    @Override
    public ResponseEntity<?> getAllCategoryService() {
        List<Category> categories = categoryRepository.findAll();

        return ResponseHandler.responseData(200, "success", categories);
    }
}