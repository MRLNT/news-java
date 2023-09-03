package id.fazzbca.news.services.category;

import org.springframework.http.ResponseEntity;

import id.fazzbca.news.payloads.req.CategoryRequest;

public interface CategoryService {
    ResponseEntity<?> addCategoryService(CategoryRequest request);

    ResponseEntity<?> getAllCategoryService();
}