package id.fazzbca.news.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import id.fazzbca.news.models.Role;

public interface RoleRepository extends JpaRepository<Role, String>{
    Role findByName(String name);
}