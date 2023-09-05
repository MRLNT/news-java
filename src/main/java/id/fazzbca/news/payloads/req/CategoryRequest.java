package id.fazzbca.news.payloads.req;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CategoryRequest {
    @NotEmpty(message = "kategori harus di isi")
    private String name;

    @NotEmpty(message = "user harus di isi")
    private String user;
}