package id.fazzbca.news.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.fazzbca.news.payloads.req.CategoryRequest;
import id.fazzbca.news.services.category.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    // register category
    @PostMapping("/register")
    public ResponseEntity<?> createCategory(@RequestBody CategoryRequest request){
        ResponseEntity<?> responseEntity = categoryService.addCategoryService(request);
        return responseEntity;
    }

    @GetMapping
    public ResponseEntity<?> getAllCategory(){
        return categoryService.getAllCategoryService();
    }
}