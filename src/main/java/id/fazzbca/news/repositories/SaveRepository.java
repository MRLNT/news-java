package id.fazzbca.news.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import id.fazzbca.news.models.Save;
import java.util.Optional; // Import Optional

public interface SaveRepository extends JpaRepository<Save, String>{
    Optional<Save> findById(String id); // Change the return type to Optional<Save>
}
