package id.fazzbca.news.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import id.fazzbca.news.models.User;

public interface UserRepository extends JpaRepository<User, String>{
    Boolean existsByUsername(String username);
    User findByUsername(String username);
}