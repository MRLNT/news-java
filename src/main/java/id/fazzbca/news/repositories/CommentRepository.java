package id.fazzbca.news.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import id.fazzbca.news.models.Comment;
import java.util.Optional; // Import Optional

public interface CommentRepository extends JpaRepository<Comment, String>{
    Optional<Comment> findById(String id);
}
