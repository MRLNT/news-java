package id.fazzbca.news.services.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import id.fazzbca.news.models.Category;
import id.fazzbca.news.payloads.req.CategoryRequest;
import id.fazzbca.news.payloads.res.ResponseHandler;
import id.fazzbca.news.repositories.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public ResponseEntity<?> addCategoryService(CategoryRequest request) {
        // cek inputan
        if (request.getName() == null || request.getName() == "") {
            throw new IllegalArgumentException("kategori dibutuhkan");
        }
        // convert request menjadi model entity
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