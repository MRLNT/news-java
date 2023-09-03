package id.fazzbca.news.payloads.req;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ArticleRequest {
    @NotEmpty(message = "judul harus di isi")
    private String title;

    @NotEmpty(message = "isi konten harus di isi")
    private String content;

    @NotEmpty(message = "user harus di isi")
    private String user;

    @NotEmpty(message = "kategori harus di isi")
    private String category;
}
