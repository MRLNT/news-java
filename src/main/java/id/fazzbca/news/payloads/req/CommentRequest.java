package id.fazzbca.news.payloads.req;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CommentRequest {    
    @NotEmpty(message = "artikel harus di isi")
    private String article;

    @NotEmpty(message = "komentar harus di isi")
    private String name;

    @NotEmpty(message = "user harus di isi")
    private String user;
}