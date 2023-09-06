package id.fazzbca.news.payloads.req;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class SaveRequest {    
    @NotEmpty(message = "artikel harus di isi")
    private String article;

    @NotEmpty(message = "komentar harus di isi")
    private String name;

    // Getter for 'article' field
    public String getArticle() {
        return article;
    }

    // Getter for 'name' field
    public String getName() {
        return name;
    }
}