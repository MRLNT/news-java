package id.fazzbca.news.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import id.fazzbca.news.models.Category;

public interface CategoryRepository extends JpaRepository<Category, String>{
    Category findByName(String name);
}