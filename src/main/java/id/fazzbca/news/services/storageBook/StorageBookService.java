package id.fazzbca.news.services.storageBook;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface StorageBookService {
  ResponseEntity<?> storeImage(MultipartFile file, String articleId) throws IOException;

  ResponseEntity<?> loadImage(String imageId);
}